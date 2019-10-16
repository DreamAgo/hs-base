package cn.loverot.base.biz.impl;

import cn.loverot.base.constant.e.TableEnum;
import cn.loverot.base.dao.IBaseDao;
import cn.loverot.base.entity.BaseEntity;
import cn.loverot.base.biz.IBaseBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseBizImpl<E extends Serializable> implements IBaseBiz {
    private IBaseDao<E> baseDao;
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public BaseBizImpl() {
    }

    @Override
    public int saveEntity(BaseEntity entity) {
        return this.getDao().saveEntity(entity);
    }

    @Override
    public void updateEntity(BaseEntity entity) {
        this.getDao().updateEntity(entity);
    }

    @Override
    public List<E> queryAll() {
        return this.getDao().queryAll();
    }

    @Override
    public BaseEntity getEntity(int id) {
        return this.getDao().getEntity(id);
    }

    @Override
    public List queryBySQL(String table, List fields, Map wheres, Integer begin, Integer end) {
        return this.getDao().queryBySQL(table, fields, wheres, begin, end, (String)null);
    }

    @Override
    public int countBySQL(String table, Map wheres) {
        return this.getDao().countBySQL(table, wheres);
    }

    @Override
    public List queryBySQL(String table, List fields, Map wheres) {
        return this.getDao().queryBySQL(table, fields, wheres, (Integer)null, (Integer)null, (String)null);
    }

    @Override
    public void updateBySQL(String table, Map fields, Map wheres) {
        this.getDao().updateBySQL(table, fields, wheres);
    }

    @Override
    public void deleteBySQL(String table, Map wheres) {
        this.getDao().deleteBySQL(table, wheres);
    }

    @Override
    public void insertBySQL(String table, Map fields) {
        this.getDao().insertBySQL(table, fields);
    }

    @Override
    public void createTable(String table, Map fileds) {
        this.getDao().createTable(table, fileds);
    }

    public void alterTable(String table, Map fileds, String type) {
        this.getDao().alterTable(table, fileds, type);
    }

    @Override
    public void alterTable(String table, Map fileds, TableEnum type) {
        this.getDao().alterTable(table, fileds, type.toString());
    }

    @Override
    public void dropTable(String table) {
        this.getDao().dropTable(table);
    }

    @Override
    public Object excuteSql(String sql) {
        return this.getDao().excuteSql(sql);
    }

    protected abstract IBaseDao<E> getDao();

    @Override
    public void saveBatch(List list) {
        this.getDao().saveBatch(list);
    }

    @Override
    public void delete(int[] ids) {
        this.getDao().delete(ids);
    }

    @Override
    public void deleteEntity(BaseEntity entity) {
        this.getDao().deleteByEntity(entity);
    }

    @Override
    public E getEntity(BaseEntity entity) {
        return this.getDao().getByEntity(entity);
    }

    @Override
    public List<E> query(BaseEntity entity) {
        return this.getDao().query(entity);
    }
}
