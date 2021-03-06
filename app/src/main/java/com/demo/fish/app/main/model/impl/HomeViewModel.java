package com.demo.fish.app.main.model.impl;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.net.Uri;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.facebook.common.util.UriUtil;
import com.demo.fish.R;
import com.demo.fish.app.main.entity.BannerEntity;
import com.demo.fish.app.main.entity.FunctionItemEntity;
import com.demo.fish.app.main.entity.HomeEntity;
import com.demo.fish.app.main.entity.HomeListEntity;
import com.demo.fish.core.mvvm.ViewLayer;
import com.demo.fish.core.mvvm.ViewModel;
import com.demo.fish.app.main.model.IHomeViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ben on 2017/4/9.
 */

public class HomeViewModel extends ViewModel implements IHomeViewModel {

    private final int PAGE_SIZE = 10;

    private HomeEntity mEntity;

    private ObservableList<HomeListEntity> mHomeList;
    private List<HomeListEntity> mFreshList;

    private List<HomeListEntity> mNearList;
    private ObservableList<BannerEntity> mBannerList;

    private ObservableList<FunctionItemEntity> mFunctionList;

    private int mRefreshListPage = 1;
    private int mNearListPage = 1;

    private Context context;

    String[] username={"朱雨婷","刘青羽","代继桥"};
    String[] desc={
            "二手手机，iphone7九成新，欢迎抢购！！！！",
            "二手书籍，各种书，欢迎抢购！！！！",
            "二手Kindle,95新，先到先得！！！！"
    };
    int[][] goodsIcon={{R.drawable.shouji1,R.drawable.shouji2,R.drawable.shouji3,
            R.drawable.shouji4,R.drawable.shouji5,R.drawable.shouji6,R.drawable.shouji7},
            {R.drawable.shuji1,R.drawable.shuji2,R.drawable.shuji3,R.drawable.shuji4,R.drawable.shuji5},
            {R.drawable.kindle1,R.drawable.kindle2,R.drawable.kindle3,R.drawable.kindle4,
                    R.drawable.kindle5,R.drawable.kindle6,R.drawable.kindle7}};
    int[] icon={R.drawable.zhuyuting,R.drawable.liuqingyu,R.drawable.daijiqiao};

    public HomeViewModel(ViewLayer viewLayer,Context context) {
        super(viewLayer);
        this.context=context;
    }

    @Override
    protected void onAttach() {
        initData();
    }

    @Override
    protected void onDetach() {

    }

    private void initData() {
        mEntity = new HomeEntity();
        mBannerList = new ObservableArrayList<>();
        mHomeList = new ObservableArrayList<>();
        mFreshList = new ArrayList<>();
        mNearList = new ArrayList<>();

        mEntity.setRefreshMoreStatus(LoadMoreView.STATUS_DEFAULT);
        mEntity.setNearMoreStatus(LoadMoreView.STATUS_DEFAULT);
        initFunctionEntities();
    }

    private void initFunctionEntities() {
        mFunctionList = new ObservableArrayList<>();
        ArrayList<String> iconUrl=new ArrayList<>();
        iconUrl.add(UriUtil.getUriForResourceId(R.drawable.dianqi).toString());
        iconUrl.add(UriUtil.getUriForResourceId(R.drawable.bao).toString());
        iconUrl.add(UriUtil.getUriForResourceId(R.drawable.nvzhuang).toString());
        iconUrl.add(UriUtil.getUriForResourceId(R.drawable.shoushi_1).toString());
        iconUrl.add(UriUtil.getUriForResourceId(R.drawable.shuma).toString());
        iconUrl.add(UriUtil.getUriForResourceId(R.drawable.baihuo).toString());

        String[] title={"居家电器","服饰鞋包","租衣服","二手首饰","数码","生活用品"};

        for (int i = 0; i < 6; i++) {
            FunctionItemEntity entity = new FunctionItemEntity();
            entity.setIconUrl(iconUrl.get(i));
            entity.setTitle(title[i]);
            entity.setDesc("");
            mFunctionList.add(entity);
        }
    }

    @Override
    public void startRefresh(boolean notify) {
        mEntity.setRefreshing(true, notify);
    }

    @Override
    public List<BannerEntity> getBannerList() {
        return mBannerList;
    }

    @Override
    public void refreshData() {
        resetData();
        loadBanner();
        loadHomeData();
    }

    private void resetData() {
        mEntity.setListType(HomeEntity.LIST_TYPE_FRESH);
        mRefreshListPage = 1;
        mNearListPage = 1;
        mEntity.setLoadingMoreStatus(LoadMoreView.STATUS_DEFAULT);
        mEntity.setRefreshMoreStatus(LoadMoreView.STATUS_DEFAULT);
        mEntity.setNearMoreStatus(LoadMoreView.STATUS_DEFAULT);
    }

    @Override
    public void loadBanner() {
        Uri[] bannerList = {
                UriUtil.getUriForResourceId(R.drawable.banner_1),
                UriUtil.getUriForResourceId(R.drawable.banner_2),
                UriUtil.getUriForResourceId(R.drawable.banner_3),
                UriUtil.getUriForResourceId(R.drawable.banner_4)
        };

        List<BannerEntity> banners = new ArrayList<>();
        for (int i = 0; i < bannerList.length; i++) {
            BannerEntity entity = new BannerEntity();
            entity.setImageUrl(bannerList[i].toString());
            banners.add(entity);
        }

        mBannerList.clear();
        mBannerList.addAll(banners);

        mEntity.setBannerCount(mBannerList.size());
    }

    @Override
    public void addBannerListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mBannerList, callback);
    }

    @Override
    public void addHomeEntityChangedCallback(Observable.OnPropertyChangedCallback callback) {
        addObservableBinding(mEntity, callback);
    }

    @Override
    public void onBannerItemClick(BannerEntity entity) {
        //TODO 事件处理
    }

    @Override
    public List<FunctionItemEntity> getFunctionList() {
        return mFunctionList;
    }

    @Override
    public void addFunctionListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mFunctionList, callback);
    }

    @Override
    public void onFunctionItemClick(FunctionItemEntity entity) {
        //TODO 事件处理

    }

    @Override
    public List<HomeListEntity> getHomeList() {
        return mHomeList;
    }

    @Override
    public void addHomeListChangedCallback(ObservableList.OnListChangedCallback callback) {
        addObservableListBinding(mHomeList, callback);
    }

    @Override
    public void loadHomeData() {
        mHomeList.clear();
        mEntity.setRefreshLoading(true);

        io.reactivex.Observable
                .create(new ObservableOnSubscribe<List<HomeListEntity>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<HomeListEntity>> e) throws Exception {
                        List<HomeListEntity> list = new ArrayList<>();
                        String message = "新鲜的 ";

                        for (int i = 1; i <= PAGE_SIZE; i++) {
                            HomeListEntity entity = new HomeListEntity();
                            entity.setName(username[(i-1)%3]);
                            entity.setDesc(desc[(i-1)%3]);
                            entity.setCommentCount(i);
                            entity.setLikeCount(i);
                            entity.setDate(new Date().toString());
                            entity.setAddress("北京");
                            entity.setGroupName(username[(i-1)%3]);
                            entity.setIconUrl(UriUtil.getUriForResourceId(icon[(i-1)%3]).toString());
                            List<String> list1 = new ArrayList<>();
                            for (int j = 0; j < goodsIcon[(i-1)%3].length; j++) {
                                list1.add(UriUtil.getUriForResourceId(goodsIcon[(i-1)%3][j]).toString());
                            }
                            entity.setPhotoList(list1);
                            entity.setLiked(i % 2 == 0 ? true : false);
                            list.add(entity);
                        }

                        Thread.sleep(2000);
                        e.onNext(list);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<HomeListEntity>>() {
                    @Override
                    public void onNext(List<HomeListEntity> homeListEntities) {
                        mFreshList.clear();
                        mNearList.clear();

                        mFreshList.addAll(homeListEntities);
                        mHomeList.clear();
                        mHomeList.addAll(mFreshList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mEntity.isRefreshLoading()) {
                            mEntity.setRefreshLoading(false);
                        }
                        mEntity.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        if (mEntity.isRefreshLoading()) {
                            mEntity.setRefreshLoading(false);
                        }
                        mEntity.setRefreshing(false);
                    }
                });
    }

    @Override
    public void changeHomeData(int position) {
        int type = position;
        if (type == mEntity.getListType()) {
            return;
        }

        mEntity.setNearLoading(false);
        mEntity.setRefreshLoading(false);
        mEntity.setListType(type, false);
        if (type == HomeEntity.LIST_TYPE_FRESH) {
            if (mFreshList.size() > 0) {
                mHomeList.clear();
                mHomeList.addAll(mFreshList);
                mEntity.setLoadingMoreStatus(mEntity.getRefreshMoreStatus());
            } else {
                loadRefreshList();
            }
        } else {
            if (mNearList.size() > 0) {
                mHomeList.clear();
                mHomeList.addAll(mNearList);
                mEntity.setLoadingMoreStatus(mEntity.getNearMoreStatus());
            } else {
                loadNearList();
            }
        }
    }

    @Override
    public void loadMore() {
        if (HomeEntity.LIST_TYPE_FRESH == mEntity.getListType()) {
            loadMoreRefreshList();
        } else {
            loadMoreNearList();
        }
    }


    private void loadRefreshList() {
        if (mEntity.isRefreshLoading()) {
            return;
        }
        mHomeList.clear();
        mEntity.setRefreshLoading(true);

        io.reactivex.Observable
                .create(new ObservableOnSubscribe<List<HomeListEntity>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<HomeListEntity>> e) throws Exception {
                        List<HomeListEntity> list = new ArrayList<>();
                        String message="新鲜的";
                        for (int i = 1; i <= PAGE_SIZE; i++) {
                            HomeListEntity entity = new HomeListEntity();
                            entity.setName(username[(i-1)%3]);
                            entity.setDesc(desc[(i-1)%3]);
                            entity.setCommentCount(i);
                            entity.setLikeCount(i);
                            entity.setDate(new Date().toString());
                            entity.setAddress("北京");
                            entity.setGroupName(username[(i-1)%3]);
                            entity.setIconUrl(UriUtil.getUriForResourceId(icon[(i-1)%3]).toString());
                            List<String> list1 = new ArrayList<>();
                            for (int j = 0; j < goodsIcon[(i-1)%3].length; j++) {
                                list1.add(UriUtil.getUriForResourceId(goodsIcon[(i-1)%3][j]).toString());
                            }
                            entity.setPhotoList(list1);
                            entity.setLiked(i % 2 == 0 ? true : false);
                            list.add(entity);
                        }

                        Thread.sleep(1000);
                        e.onNext(list);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<HomeListEntity>>() {
                    @Override
                    public void onNext(List<HomeListEntity> homeListEntities) {
                        mFreshList.clear();
                        mFreshList.addAll(homeListEntities);

                        if (HomeEntity.LIST_TYPE_FRESH == mEntity.getListType()) {
                            mHomeList.addAll(mFreshList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mEntity.isRefreshLoading()) {
                            mEntity.setRefreshLoading(false);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mEntity.isRefreshLoading()) {
                            mEntity.setRefreshLoading(false);
                        }
                    }
                });
    }
    private void loadNearList() {
        if (mEntity.isNearLoading()) {
            return;
        }
        mHomeList.clear();
        mEntity.setNearLoading(true);

        io.reactivex.Observable
                .create(new ObservableOnSubscribe<List<HomeListEntity>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<HomeListEntity>> e) throws Exception {
                        List<HomeListEntity> list = new ArrayList<>();
                        String message = "附近的 ";

                        for (int i = 1; i <= PAGE_SIZE; i++) {
                            HomeListEntity entity = new HomeListEntity();
                            entity.setName(username[(i-1)%3]);
                            entity.setDesc(desc[(i-1)%3]);
                            entity.setCommentCount(i);
                            entity.setLikeCount(i);
                            entity.setDate(new Date().toString());
                            entity.setAddress("北京");
                            entity.setGroupName(username[(i-1)%3]);
                            entity.setIconUrl(UriUtil.getUriForResourceId(icon[(i-1)%3]).toString());
                            List<String> list1 = new ArrayList<>();
                            for (int j = 0; j < goodsIcon[(i-1)%3].length; j++) {
                                list1.add(UriUtil.getUriForResourceId(goodsIcon[(i-1)%3][j]).toString());
                            }
                            entity.setLiked(i % 2 == 0 ? true : false);
                            entity.setPhotoList(list1);
                            list.add(entity);
                        }

                        Thread.sleep(1000);
                        e.onNext(list);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<HomeListEntity>>() {
                    @Override
                    public void onNext(List<HomeListEntity> homeListEntities) {
                        mNearList.clear();
                        mNearList.addAll(homeListEntities);

                        if (HomeEntity.LIST_TYPE_NEAR == mEntity.getListType()) {
                            mHomeList.clear();
                            mHomeList.addAll(mNearList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mEntity.isNearLoading()) {
                            mEntity.setNearLoading(false);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mEntity.isNearLoading()) {
                            mEntity.setNearLoading(false);
                        }
                    }
                });
    }

    private void loadMoreRefreshList() {
        mEntity.setRefreshMoreStatus(LoadMoreView.STATUS_LOADING);
        mEntity.setLoadingMoreStatus(mEntity.getRefreshMoreStatus(), false);

        io.reactivex.Observable
                .create(new ObservableOnSubscribe<List<HomeListEntity>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<HomeListEntity>> e) throws Exception {
                        List<HomeListEntity> list = new ArrayList<>();
                        String message = "新鲜的 ";
                        int size;
                        if (mRefreshListPage > 2) {
                            size = mRefreshListPage * PAGE_SIZE + 5;
                        } else {
                            size = (mRefreshListPage + 1) * PAGE_SIZE;
                        }

                        for (int i = mRefreshListPage * PAGE_SIZE + 1; i <= size; i++) {
                            HomeListEntity entity = new HomeListEntity();
                            entity.setName(username[(i-1)%3]);
                            entity.setDesc(desc[(i-1)%3]);
                            entity.setCommentCount(i);
                            entity.setLikeCount(i);
                            entity.setDate(new Date().toString());
                            entity.setAddress("北京");
                            entity.setGroupName(username[(i-1)%3]);
                            entity.setIconUrl(UriUtil.getUriForResourceId(icon[(i-1)%3]).toString());
                            List<String> list1 = new ArrayList<>();
                            for (int j = 0; j < goodsIcon[(i-1)%3].length; j++) {
                                list1.add(UriUtil.getUriForResourceId(goodsIcon[(i-1)%3][j]).toString());
                            }
                            entity.setLiked(i % 2 == 0 ? true : false);
                            entity.setPhotoList(list1);
                            list.add(entity);
                        }

                        Thread.sleep(1000);
                        e.onNext(list);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<HomeListEntity>>() {
                    @Override
                    public void onNext(List<HomeListEntity> homeListEntities) {
                        if (null != homeListEntities && homeListEntities.size() > 0) {
                            mFreshList.addAll(homeListEntities);

                            if (HomeEntity.LIST_TYPE_FRESH == mEntity.getListType()) {
                                mHomeList.addAll(homeListEntities);
                            }
                        }

                        if (null == homeListEntities
                                || homeListEntities.size() < PAGE_SIZE) {
                            mEntity.setRefreshMoreStatus(LoadMoreView.STATUS_END);
                            if (HomeEntity.LIST_TYPE_FRESH == mEntity.getListType()) {
                                mEntity.setLoadingMoreStatus(mEntity.getRefreshMoreStatus());
                            }
                        } else {
                            mEntity.setRefreshMoreStatus(LoadMoreView.STATUS_DEFAULT);
                            if (HomeEntity.LIST_TYPE_FRESH == mEntity.getListType()) {
                                mEntity.setLoadingMoreStatus(mEntity.getRefreshMoreStatus());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mEntity.setRefreshMoreStatus(LoadMoreView.STATUS_FAIL);
                        if (HomeEntity.LIST_TYPE_FRESH == mEntity.getListType()) {
                            mEntity.setLoadingMoreStatus(mEntity.getRefreshMoreStatus());
                        }
                    }

                    @Override
                    public void onComplete() {
                        mRefreshListPage++;
                    }
                });
    }

    private void loadMoreNearList() {
        mEntity.setNearMoreStatus(LoadMoreView.STATUS_LOADING);
        mEntity.setLoadingMoreStatus(mEntity.getNearMoreStatus(), false);

        io.reactivex.Observable
                .create(new ObservableOnSubscribe<List<HomeListEntity>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<HomeListEntity>> e) throws Exception {
                        List<HomeListEntity> list = new ArrayList<>();
                        String message = "附近的 ";
                        int size;
                        if (mNearListPage > 2) {
                            size = mNearListPage * PAGE_SIZE + 5;
                        } else {
                            size = (mNearListPage + 1) * PAGE_SIZE;
                        }
                        for (int i = mNearListPage * PAGE_SIZE + 1; i <= size; i++) {
                            HomeListEntity entity = new HomeListEntity();
                            entity.setName(username[(i-1)%3]);
                            entity.setDesc(desc[(i-1)%3]);
                            entity.setCommentCount(i);
                            entity.setLikeCount(i);
                            entity.setDate(new Date().toString());
                            entity.setAddress("北京");
                            entity.setGroupName(username[(i-1)%3]);
                            entity.setIconUrl(UriUtil.getUriForResourceId(icon[(i-1)%3]).toString());
                            List<String> list1 = new ArrayList<>();
                            for (int j = 0; j < goodsIcon[(i-1)%3].length; j++) {
                                list1.add(UriUtil.getUriForResourceId(goodsIcon[(i-1)%3][j]).toString());
                            }
                            entity.setLiked(i % 2 == 0 ? true : false);
                            entity.setPhotoList(list1);
                            list.add(entity);
                        }

                        Thread.sleep(1000);
                        e.onNext(list);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<HomeListEntity>>() {
                    @Override
                    public void onNext(List<HomeListEntity> homeListEntities) {
                        if (null != homeListEntities && homeListEntities.size() > 0) {
                            mNearList.addAll(homeListEntities);

                            if (HomeEntity.LIST_TYPE_NEAR == mEntity.getListType()) {
                                mHomeList.addAll(homeListEntities);
                            }
                        }

                        if (null == homeListEntities
                                || homeListEntities.size() < PAGE_SIZE) {
                            mEntity.setNearMoreStatus(LoadMoreView.STATUS_END);
                            if (HomeEntity.LIST_TYPE_NEAR == mEntity.getListType()) {
                                mEntity.setLoadingMoreStatus(mEntity.getNearMoreStatus());
                            }
                        } else {
                            mEntity.setNearMoreStatus(LoadMoreView.STATUS_DEFAULT);
                            if (HomeEntity.LIST_TYPE_NEAR == mEntity.getListType()) {
                                mEntity.setLoadingMoreStatus(mEntity.getNearMoreStatus());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mEntity.setNearMoreStatus(LoadMoreView.STATUS_FAIL);
                        if (HomeEntity.LIST_TYPE_NEAR == mEntity.getListType()) {
                            mEntity.setLoadingMoreStatus(mEntity.getNearMoreStatus());
                        }
                    }

                    @Override
                    public void onComplete() {
                        mNearListPage++;
                    }
                });
    }

}
