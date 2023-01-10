package com.zxy.dao;

import com.zxy.entity.Myfile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyfileMapper {
    /**
     * 上传文件
     * @param myfile
     * @return
     */
    public Integer uploadFiles(Myfile myfile);

    /**
     * 获取使用空间
     * @param user_id
     * @return
     */
    public float findUseSpace(Integer user_id);

    /**
     * 判断myfile表是否有数据
     * @return
     */
    public int findIdCounts(Integer user_id);
    /**
     * 获取所有文件
     * @param id
     * @return
     */
    public List<Myfile> findAllFiles(Integer id);

    /**
     * 获取单个文件
     * @param id
     * @return
     */
    public Myfile findFileById(Integer id);

    /**
     * 修改下载次数
     * @param id
     * @return
     */
    public Integer setDownLoadCounts(Integer id);

    /**
     * 设置分享
     * @param myfile
     * @return
     */
    public Integer setShares(Myfile myfile);
    /**
     * 设置收藏文件
     * @param myfile
     * @return
     */
    public Integer setFavorites(Myfile myfile);

    /**
     * 回收文件
     * @param id
     * @return
     */
    public Integer setRecycles(Integer id);

    /**
     * 公开文件
     * @param myfile
     * @return
     */
    public Integer setPublics(Myfile myfile);

    /**
     * 关键字模糊查询
     * @param myfile
     * @return
     */
    public List<Myfile> findFilesByKey(Myfile myfile);

    /**
     * 获取所有图片
     * @param myfile
     * @return
     */
    public List<Myfile> findAllImages(Myfile myfile);

    /**
     * 获取所有收藏文件
     * @return
     */
    public List<Myfile> findAllFavorites(Myfile myfile);

    /**
     * 获取所有共享文件
     * @return
     */
    public List<Myfile> findAllPublics(Myfile myfile);

    /**
     * 获取所有分享文件
     * @return
     */
    public List<Myfile> findAllShares(Myfile myfile);

    /**
     * 获取所有回收文件
     * @return
     */
    public List<Myfile> findAllRecycles(Integer user_id);

    /**
     * 永久删除
     * @param id
     * @return
     */
    public Integer deleteAllFiles(Integer id);

    /**
     * 回收文件
     * @param id
     * @return
     */
    public Integer recoverAllFiles(Integer id);

    /**
     * 获取所有人共享文件
     * @param myfile
     * @return
     */
    public List<Myfile> findAllPersonPublic(Myfile myfile);


}
