package chengweiou.universe.leob.data;


import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Device;
import chengweiou.universe.leob.service.device.DeviceDio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Data {
    @Autowired
    private DeviceDio deviceDio;
    public List<Device> deviceList;

    public void init() {
        deviceList = deviceDio.find(new SearchCondition(), new Device()).stream().sorted(Comparator.comparingLong(Device::getId)).collect(Collectors.toList());
    }
}
