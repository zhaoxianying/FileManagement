package com.zxy.service.Impl;

import com.zxy.dao.MyfileMapper;
import com.zxy.entity.Myfile;
import com.zxy.service.MyfileService;
import com.zxy.utils.FileTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MyfileServiceImpl implements MyfileService {
    @Autowired
    private MyfileMapper myfileMapper;
    @Autowired
    private FileTypeUtils fileTypeUtils;
    /**
     * 上传文件
     * @param myfile
     * @return
     */
    @Override
    public Integer uploadFiles(Myfile myfile) throws ParseException {
        //获取系统当前时间
        //生成日期对象
        Date current_date = new Date();
        //设置日期格式化样式为：yyyy-MM-dd
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String uploadtime = simpleDateFormat.format(current_date);
        //设置上传时间和修改时间
        myfile.setUploadtime(simpleDateFormat.parse(uploadtime));
        myfile.setChangetime(simpleDateFormat.parse(uploadtime));
        //设置是否为图片
        boolean isima = myfile.getType().startsWith("image");
        if (isima){
            myfile.setIsimg(1);
        }else{
            myfile.setIsimg(0);
        }
        return this.myfileMapper.uploadFiles(myfile);
    }

    /**
     * 获取使用空间
     * @param user_id
     * @return
     */
    @Override
    public float findUseSpace(Integer user_id) {
        return this.myfileMapper.findUseSpace(user_id);
    }

    /**
     * 判断myfile表是否有数据
     * @return
     */
    @Override
    public int findIdCounts(Integer user_id) {
        return this.myfileMapper.findIdCounts(user_id);
    }

    /**
     * 获取所有文件
     * @param id
     * @return
     */
    @Override
    public List<Myfile> findAllFiles(Integer id) {
        List<Myfile> allFiles = this.myfileMapper.findAllFiles(id);
        return this.fileTypeUtils.IconFilesList(allFiles);
    }

    /**
     * 获取单个文件
     * @param id
     * @return
     */
    @Override
    public Myfile findFileById(Integer id) {
        return this.myfileMapper.findFileById(id);
    }

    /**
     * 修改下载次数
     * @param id
     * @return
     */
    @Override
    public Integer setDownLoadCounts(Integer id) {
        return this.myfileMapper.setDownLoadCounts(id);
    }

    /**
     * 设置分享
     * @param myfile
     * @return
     */
    @Override
    public Integer setShares(Myfile myfile) {
        return this.myfileMapper.setShares(myfile);
    }

    /**
     * 收藏文件
     * @param myfile
     * @return
     */
    @Override
    public Integer setFavorites(Myfile myfile) {
        return this.myfileMapper.setFavorites(myfile);
    }

    /**
     * 回收文件
     * @param id
     * @return
     */
    @Override
    public Integer setRecycles(Integer id) {
        return this.myfileMapper.setRecycles(id);
    }

    /**
     * 公开文件
     * @param myfile
     * @return
     */
    @Override
    public Integer setPublics(Myfile myfile) {
        return this.myfileMapper.setPublics(myfile);
    }

    /**
     * 关键字模糊查询
     * @param myfile
     * @return
     */
    @Override
    public List<Myfile> findFilesByKey(Myfile myfile) {
        List<Myfile> allFiles = this.myfileMapper.findFilesByKey(myfile);
        return this.fileTypeUtils.IconFilesList(allFiles);
    }

    /**
     * 获取所有图片
     * @param myfile
     * @return
     */
    @Override
    public List<Myfile> findAllImages(Myfile myfile) {
        return this.myfileMapper.findAllImages(myfile);
    }

    /**
     * 获得所有收藏文件
     * @param myfile
     * @return
     */
    @Override
    public List<Myfile> findAllFavorites(Myfile myfile) {
        List<Myfile> allFavorites = this.myfileMapper.findAllFavorites(myfile);
        return this.fileTypeUtils.IconFilesList(allFavorites);
    }

    /**
     * 获取所有共享文件
     * @param myfile
     * @return
     */
    @Override
    public List<Myfile> findAllPublics(Myfile myfile) {
        List<Myfile> allPublics = this.myfileMapper.findAllPublics(myfile);
        return this.fileTypeUtils.IconFilesList(allPublics);
    }

    /**
     * 获取所有分享文件
     * @param myfile
     * @return
     */
    @Override
    public List<Myfile> findAllShares(Myfile myfile) {
        List<Myfile> allShares = this.myfileMapper.findAllShares(myfile);
        return this.fileTypeUtils.IconFilesList(allShares);
    }

    /**
     * 获取所有回收文件
     * @param user_id
     * @return
     */
    @Override
    public List<Myfile> findAllRecycles(Integer user_id) {
        List<Myfile> allRecycles = this.myfileMapper.findAllRecycles(user_id);
        return this.fileTypeUtils.IconFilesList(allRecycles);
    }

    /**
     * 永久删除
     * @param id
     * @return
     */
    @Override
    public Integer deleteAllFiles(Integer id) {
        return this.myfileMapper.deleteAllFiles(id);
    }

    /**
     * 回收文件
     * @param id
     * @return
     */
    @Override
    public Integer recoverAllFiles(Integer id) {
        return this.myfileMapper.recoverAllFiles(id);
    }

    /**
     * 获取所有人的共享文件
     * @param myfile
     * @return
     */
    @Override
    public List<Myfile> findAllPersonPublic(Myfile myfile) {
        List<Myfile> allPersonPublic = this.myfileMapper.findAllPersonPublic(myfile);
        return this.fileTypeUtils.IconFilesList(allPersonPublic);
    }
}
