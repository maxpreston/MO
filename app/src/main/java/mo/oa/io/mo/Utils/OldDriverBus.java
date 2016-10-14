package mo.oa.io.mo.Utils;

import android.os.Bundle;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by max-code on 2016/10/10.
 * WelCome to OldDriverBus
 */

public class OldDriverBus {

    private final Subject<Object,Object> rxBus = new SerializedSubject<>(PublishSubject.create());

    public OldDriverBus() {
    }
    public static OldDriverBus getOldDriver(){
        return RxBusholder.instance;
    }
    public static class RxBusholder{
        private static final OldDriverBus instance = new OldDriverBus();
    }
    //老司机开车
    public void sendBus(Object bundle){
        rxBus.onNext(bundle);
    }
    //老司机收车
    public Observable<Object> ToObserable(){
        return rxBus;
    }
    //根据类型进行过滤oftype

}
