package demo.great.zhang.poket.entity;

public class FlowerBean {

    /**
     * countm : 4
     * redData : {"redShujuId":1,"gzAllNum":1,"djJieduan":4,"yinlingzhe":3,"tuigz":4,"xxjinli":5,"addTime":"2019-07-28 19:03:09","editTime":null}
     * syall : 110.0
     */

    private int countm;
    private int yeUsdt;
    private int nmustYe;
    private int yduihUSDT;
    private RedDataBean redData;
    private double syall;
    private String shoubiAddress;

    public int getYeUsdt() {
        return yeUsdt;
    }

    public void setYeUsdt(int yeUsdt) {
        this.yeUsdt = yeUsdt;
    }

    public int getNmustYe() {
        return nmustYe;
    }

    public void setNmustYe(int nmustYe) {
        this.nmustYe = nmustYe;
    }

    public int getYduihUSDT() {
        return yduihUSDT;
    }

    public void setYduihUSDT(int yduihUSDT) {
        this.yduihUSDT = yduihUSDT;
    }

    public String getShoubiAddress() {
        return shoubiAddress;
    }

    public void setShoubiAddress(String shoubiAddress) {
        this.shoubiAddress = shoubiAddress;
    }

    public int getCountm() {
        return countm;
    }

    public void setCountm(int countm) {
        this.countm = countm;
    }

    public RedDataBean getRedData() {
        return redData;
    }

    public void setRedData(RedDataBean redData) {
        this.redData = redData;
    }

    public double getSyall() {
        return syall;
    }

    public void setSyall(double syall) {
        this.syall = syall;
    }

    public static class RedDataBean {
        /**
         * redShujuId : 1
         * gzAllNum : 1.0
         * djJieduan : 4
         * yinlingzhe : 3.0
         * tuigz : 4.0
         * xxjinli : 5.0
         * addTime : 2019-07-28 19:03:09
         * editTime : null
         */

        private int redShujuId;
        private double gzAllNum;
        private int djJieduan;
        private double yinlingzhe;
        private double tuigz;
        private double xxjinli;
        private String addTime;
        private Object editTime;

        public int getRedShujuId() {
            return redShujuId;
        }

        public void setRedShujuId(int redShujuId) {
            this.redShujuId = redShujuId;
        }

        public double getGzAllNum() {
            return gzAllNum;
        }

        public void setGzAllNum(double gzAllNum) {
            this.gzAllNum = gzAllNum;
        }

        public int getDjJieduan() {
            return djJieduan;
        }

        public void setDjJieduan(int djJieduan) {
            this.djJieduan = djJieduan;
        }

        public double getYinlingzhe() {
            return yinlingzhe;
        }

        public void setYinlingzhe(double yinlingzhe) {
            this.yinlingzhe = yinlingzhe;
        }

        public double getTuigz() {
            return tuigz;
        }

        public void setTuigz(double tuigz) {
            this.tuigz = tuigz;
        }

        public double getXxjinli() {
            return xxjinli;
        }

        public void setXxjinli(double xxjinli) {
            this.xxjinli = xxjinli;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public Object getEditTime() {
            return editTime;
        }

        public void setEditTime(Object editTime) {
            this.editTime = editTime;
        }
    }
}
