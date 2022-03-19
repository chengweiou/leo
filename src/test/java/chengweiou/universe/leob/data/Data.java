package chengweiou.universe.leob.data;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.model.Person;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Device;
import chengweiou.universe.leob.model.entity.notify.Notify;
import chengweiou.universe.leob.service.device.DeviceDio;
import chengweiou.universe.leob.service.notify.NotifyDio;

@Component
public class Data {
    public List<Person> personList;
    @Autowired
    private DeviceDio deviceDio;
    public List<Device> deviceList;
    @Autowired
    private NotifyDio notifyDio;
    public List<Notify> notifyList;


    public void init() {
        personList = Stream.iterate(1, i -> i + 1).limit(10).map(i -> Builder.set("id", i).to(new Person())).toList();
        deviceList = deviceDio.find(new SearchCondition(), new Device()).stream().sorted(Comparator.comparingLong(Device::getId)).toList();
        notifyList = notifyDio.find(new SearchCondition(), new Notify()).stream().sorted(Comparator.comparingLong(Notify::getId)).toList();
    }
}
