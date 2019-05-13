package cn.loverot.base.service.impl;

import cn.loverot.base.constant.e.TableEnum;
import cn.loverot.base.dao.IBaseDao;
import cn.loverot.base.entity.BaseEntity;
import cn.loverot.base.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl <E extends Serializable> implements IBaseService {
    private IBaseDao<E> baseDao;
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public BaseServiceImpl() {
    }

    public int saveEntity(BaseEntity entity) {
        return this.getDao().saveEntity(entity);
    }


    public void updateEntity(BaseEntity entity) {
        this.getDao().updateEntity(entity);
    }

    public List<E> queryAll() {
        return this.getDao().queryAll();
    }


    public BaseEntity getEntity(int id) {
        return this.getDao().getEntity(id);
    }

    public List queryBySQL(String table, List fields, Map wheres, Integer begin, Integer end) {
        return this.getDao().queryBySQL(table, fields, wheres, begin, end, (String)null);
    }

    public int countBySQL(String table, Map wheres) {
        return this.getDao().countBySQL(table, wheres);
    }

    public List queryBySQL(String table, List fields, Map wheres) {
        return this.getDao().queryBySQL(table, fields, wheres, (Integer)null, (Integer)null, (String)null);
    }

    public void updateBySQL(String table, Map fields, Map wheres) {
        this.getDao().updateBySQL(table, fields, wheres);
    }

    public void deleteBySQL(String table, Map wheres) {
        this.getDao().deleteBySQL(table, wheres);
    }

    public void insertBySQL(String table, Map fields) {
        this.getDao().insertBySQL(table, fields);
    }

    public void createTable(String table, Map fileds) {
        this.getDao().createTable(table, fileds);
    }

    public void alterTable(String table, Map fileds, String type) {
        this.getDao().alterTable(table, fileds, type);
    }

    public void alterTable(String table, Map fileds, TableEnum type) {
        this.getDao().alterTable(table, fileds, type.toString());
    }

    public void dropTable(String table) {
        this.getDao().dropTable(table);
    }

    public Object excuteSql(String sql) {
        return this.getDao().excuteSql(sql);
    }

    protected abstract IBaseDao<E> getDao();

    public void saveBatch(List list) {
        this.getDao().saveBatch(list);
    }

    public void delete(int[] ids) {
        this.getDao().delete(ids);
    }

    public void deleteEntity(BaseEntity entity) {
        this.getDao().deleteByEntity(entity);
    }

    public E getEntity(BaseEntity entity) {
        return this.getDao().getByEntity(entity);
    }

    public List<E> query(BaseEntity entity) {
        return this.getDao().query(entity);
    }
}
