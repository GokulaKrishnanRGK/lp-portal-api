package com.sw.lp.service;

import com.sw.lp.dao.UnitsDao;
import com.sw.lp.record.Unit;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("unitsService")
public class UnitsService {

  private UnitsDao unitsDao;

  public UnitsService(UnitsDao unitsDao) {
    this.unitsDao = unitsDao;
  }

  public void saveUnits(List<Unit> units, String millId) {
    this.unitsDao.saveUnits(units, millId);
  }

  public List<Unit> getUnits(String millId) {
    return this.unitsDao.getAllUnits(millId);
  }

}
