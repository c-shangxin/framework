package entity;

import zhujie.column;

/**
 * Created by csx on 2017/12/7.
 */
public class emp {
    private int eid;
    private String ename;
    @column("epwd")
    private String epwd;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEpwd() {
        return epwd;
    }

    public void setEpwd(String epwd) {
        this.epwd = epwd;
    }
}
