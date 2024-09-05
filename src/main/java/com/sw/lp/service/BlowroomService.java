package com.sw.lp.service;

import com.sw.lp.dao.BlowroomDao;
import com.sw.lp.record.BlowroomLine;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("blowroomService")
public class BlowroomService {

  private static final Logger logger = LoggerFactory.getLogger(BlowroomService.class);

  private BlowroomDao blowroomDao;

  public BlowroomService(BlowroomDao blowroomDao) {
    this.blowroomDao = blowroomDao;
  }

  public void newBlowroomLine(BlowroomLine blowroomLine, String millId) {
    this.blowroomDao.newBlowroomLine(blowroomLine, millId);
  }

  //TODO
  public List<BlowroomLine> getAllBlowroomLines(String millId) {
    String sql = "SELECT * FROM blowroom_line JOIN blowroom_line_machine_mapping  WHERE mill_id = ?";
    return null;
  }

}
