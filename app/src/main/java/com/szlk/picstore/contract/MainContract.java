package com.szlk.picstore.contract;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import com.szlk.picstore.BasePresenter;
import com.szlk.picstore.BaseView;
import java.util.List;

/**
 * Created by C.C on 2016/8/18.
 */

public interface MainContract {
    interface MainBiz{
        void loadPic(Activity activity, onLoadPicListener onLoadPicListener);
        interface onLoadPicListener{
            void onLoadBefore();
            void onLoadAfter();
            void onResult(List<String>picPathList);
            void onFailed(String info);
        }
    }

    interface View extends BaseView<Presenter>{
        void showProgress();
        void closeProgress();
        void showInfo(String info);
    }

    interface Presenter extends BasePresenter{
        void getRecyclerView(RecyclerView recyclerView);
        void load(Activity activity);
        void recycle();
    }
}
