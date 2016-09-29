package mo.oa.io.mo.Utils;

import android.support.annotation.IntDef;

import java.util.List;

import mo.oa.io.mo.R;
import mo.oa.io.mo.UI.Base.App;
import rx.subjects.BehaviorSubject;

/**
 * Created by max-code on 2016/9/25.
 */

public class DATA {
    private DATA data;
    private static final int DATA_FROM_DISK = 0;
    private static final int DATA_FROM_CACHE = 1;
    private static final int DATA_FROM_NET = 2;

    @IntDef({DATA_FROM_DISK,DATA_FROM_CACHE,DATA_FROM_NET})@interface DAtaSource{}

    BehaviorSubject<List<String>> bs = null;

    private int datasource;

    public DATA() {
    }

    public void setDatasource(@DAtaSource int datasource) {
        this.datasource = datasource;
    }
    //查找数据源
    public String getDATASource(){
        int dataStringid;
        switch(datasource){
            case DATA_FROM_DISK: {
                dataStringid = R.string.DATA_FROM_DISK;
            }
            break;
            case DATA_FROM_CACHE: {
                dataStringid = R.string.DATA_FROM_CACHE;
            }
            break;
            case DATA_FROM_NET: {
                dataStringid = R.string.DATA_FROM_NET;
            }
            break;
            default:dataStringid = R.string.DATA_FROM_NET;
        }
        return App.getAppinstance().getString(dataStringid);
    }
    //加载来源于网络数据
    public void LoadDatAFromNet(){

    }

}
