package com.sw.lp.record;

import com.sw.lp.constants.BlendType;
import com.sw.lp.constants.EnduseType;
import com.sw.lp.constants.MaterialBlendType;
import com.sw.lp.constants.MaterialType;
import com.sw.lp.constants.ProcessType;
import com.sw.lp.constants.SpinningTechType;

public record Product(int productId, String name, String description, int unitId, int millId, int count, MaterialType materialType,
                      ProcessType processType, BlendType blendType, SpinningTechType spinningTechType, EnduseType enduseType,
                      MaterialBlendType materialBlendType, String code) {

}
