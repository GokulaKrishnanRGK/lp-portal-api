package com.sw.lp.record;

import java.util.List;

public record BlowroomLine(String blowroomId, String unitId, String name, String description,
                           List<MachinePositionMapping> machinePositionMappings) {

}
