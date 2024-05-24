package com.sw.lp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

  private static final Gson GSON;

  private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

  /*public static final ExclusionStrategy EXCLUDE_FIELD_STRATEGY = new ExclusionStrategy() {
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
      return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes field) {
      return field.getAnnotation(ExcludeField.class) != null;
    }
  };*/

  static {
    GsonBuilder gsonBldr = new GsonBuilder();
    gsonBldr.serializeSpecialFloatingPointValues();
    GSON = gsonBldr.create();
  }

  private JsonUtils() {
    throw new AssertionError("No com.sw.ofs.commons.utils.JsonUtils instances for you!");
  }

  public static JsonElement asJsonElement(String data) {
    return JsonParser.parseString(data);
  }

  public static JsonObject asJsonObject(String data) {
    return (JsonObject) JsonParser.parseString(data);
  }

  /**
   * Returns a string of data as {@link JsonObject} with key
   *
   * <pre>
   * String str = "{"name": "eventTime", "value": "1000"}";
   *
   * JsonUtils.asJsonObject("key", str) =
   *
   * {
   *  "key": {
   *     "name": "eventTime",
   *     "value": "1000"
   *   }
   * }
   * </pre>
   *
   * @param key
   * @param data
   * @return
   */
  public static JsonObject asJsonObject(String key, String data) {
    JsonObject j = new JsonObject();
    j.add(key, JsonParser.parseString(data));
    return j;
  }

  public static JsonObject asJsonObject(Path dataPath) throws IOException {
    return asJsonObject(new String(Files.readAllBytes(dataPath)));
  }

  public static JsonArray asJsonArray(Path dataPath) throws IOException {
    return asJsonArray(new String(Files.readAllBytes(dataPath)));
  }

  public static JsonArray asJsonArray(String data) {
    return JsonParser.parseString(data).getAsJsonArray();
  }

  public static <T> String asJsonString(T obj) {
    return GSON.toJson(obj);
  }

  public static <T> String asJsonString(List<T> collection) {
    return GSON.toJson(collection);
  }

  public static <T> T jsonToObject(String jsonString, Class<T> obj) {
    return GSON.fromJson(jsonString, obj);
  }

  public static <T> T jsonToObject(JsonObject source, Class<T> obj) {
    return GSON.fromJson(source, obj);
  }

  public static <T> String objectToJsonString(String jsonString, Class<T> obj) {
    return GSON.toJson(jsonString, obj);
  }

  public static <T> List<T> asList(String jsonString, Type t) {
    return GSON.fromJson(jsonString, t);
  }

  public static <T> List<T> asList(JsonArray jsonString, Type t) {
    return GSON.fromJson(jsonString, t);
  }

  public static <T> T transform(JsonElement jsonString, Type t) {
    return GSON.fromJson(jsonString, t);
  }

  public static <T> T transform(String jsonString, Type t) {
    return GSON.fromJson(jsonString, t);
  }

  public static <T> T transform(Path path, Type t) throws IOException {
    return GSON.fromJson(new String(Files.readAllBytes(path)), t);
  }

  public static String transform(Object jsonString) {
    return GSON.toJson(jsonString);
  }

  public static JsonElement transformTree(Object jsonString) {
    return GSON.toJsonTree(jsonString);
  }

  public static Object[] toArray(JsonElement e) {
    return GSON.fromJson(e, Object[].class);
  }

  public static boolean isValidJson(String jsonString) {
    try {
      GSON.fromJson(jsonString, Object.class);
      return true;
    } catch (JsonSyntaxException ex) {
      logger.warn("Not a valid JSON String", ex);
      return false;
    }
  }

  public enum MergeStrategy {
    THROW_EXCEPTION,
    PREFER_FIRST_OBJ,
    PREFER_SECOND_OBJ,
    PREFER_NON_NULL;
  }

  public static class JsonMergeException extends Exception {

    public JsonMergeException(String message) {
      super(message);
    }
  }

  public static void extend(JsonObject destination, MergeStrategy conflictResolutionStrategy, JsonObject... objs)
      throws JsonMergeException {
    for (JsonObject obj : objs) {
      extend(destination, obj, conflictResolutionStrategy);
    }
  }

  private static void extend(JsonObject left, JsonObject right, MergeStrategy mergeStrategy) throws JsonMergeException {
    for (Map.Entry<String, JsonElement> rightEntry : right.entrySet()) {
      String rightKey = rightEntry.getKey();
      JsonElement rightVal = rightEntry.getValue();
      if (left.has(rightKey)) {
        //conflict
        JsonElement leftVal = left.get(rightKey);
        if (leftVal.isJsonArray() && rightVal.isJsonArray()) {
          // JsonArray leftArr = leftVal.getAsJsonArray();
          JsonArray rightArr = rightVal.getAsJsonArray();
          //concat the arrays -- there cannot be a conflict in an array, it's just a collection of stuff
          /*for (int i = 0; i < rightArr.size(); i++) {
            leftArr.add(rightArr.get(i));
          }*/
          left.add(rightKey, rightArr);
        } else if (leftVal.isJsonObject() && rightVal.isJsonObject()) {
          extend(leftVal.getAsJsonObject(), rightVal.getAsJsonObject(), mergeStrategy);
        } else {//not both arrays or objects, normal merge with conflict resolution
          handleMergeConflict(rightKey, left, leftVal, rightVal, mergeStrategy);
        }
      } else {//no conflict, add to the object
        left.add(rightKey, rightVal);
      }
    }
  }

  private static void handleMergeConflict(String key, JsonObject leftObj, JsonElement leftVal, JsonElement rightVal,
      MergeStrategy mergeStrategy) throws JsonMergeException {
    switch (mergeStrategy) {
      case PREFER_FIRST_OBJ:
        break;//do nothing, the right val gets thrown out
      case PREFER_SECOND_OBJ:
        leftObj.add(key, rightVal);//right side auto-wins, replace left val with its val
        break;
      case PREFER_NON_NULL:
        //check if right side is not null, and left side is null, in which case we use the right val
        if (leftVal.isJsonNull() && !rightVal.isJsonNull()) {
          leftObj.add(key, rightVal);
        }//else do nothing since either the left value is non-null or the right value is null
        break;
      case THROW_EXCEPTION:
        throw new JsonMergeException("Key " + key + " exists in both objects");
      default:
        throw new UnsupportedOperationException("Unknown merge strategy " + mergeStrategy);
    }
  }

  public static JsonElement ofNullable(JsonElement element) {
    return null != element && element.isJsonNull() ? null : element;
  }

}
