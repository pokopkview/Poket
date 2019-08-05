package demo.great.zhang.poket.entity;

import java.util.List;

public class MeberDetail {

        private MemberBean member;
        private List<DbBean> db;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public List<DbBean> getDb() {
            return db;
        }

        public void setDb(List<DbBean> db) {
            this.db = db;
        }

        public static class MemberBean {
            /**
             * memberId : 60000
             * ssMemberId : 0
             * nickname : wu
             * isRed : N
             * walletName : NFAS
             * etcNum : 100.0
             * jhetcNum : 20.0
             * noUSDT : 14.0
             * memberHead : null
             * memberPassword : e10adc3949ba59abbe56e057f20f883e
             * mobile : 18913102826
             * mobileCode : 587361
             * realName : null
             * bankAccount : null
             * isDelete : N
             * addTime : 2019-07-20 23:21:48
             * addUserId : null
             * editTime : 2018-12-24 18:54:31
             * editUserId : null
             * mobileActivation : Y
             * syetcNum : 14.0
             * usdtfee : 516.8
             * shoubiAddress : 222222222222222
             */

            private int memberId;
            private int ssMemberId;
            private String nickname;
            private String isRed;
            private String walletName;
            private double etcNum;
            private double jhetcNum;
            private double noUSDT;
            private Object memberHead;
            private String memberPassword;
            private String mobile;
            private String mobileCode;
            private Object realName;
            private Object bankAccount;
            private String isDelete;
            private String addTime;
            private Object addUserId;
            private String editTime;
            private Object editUserId;
            private String mobileActivation;
            private double syetcNum;
            private double usdtfee;
            private String shoubiAddress;

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getSsMemberId() {
                return ssMemberId;
            }

            public void setSsMemberId(int ssMemberId) {
                this.ssMemberId = ssMemberId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getIsRed() {
                return isRed;
            }

            public void setIsRed(String isRed) {
                this.isRed = isRed;
            }

            public String getWalletName() {
                return walletName;
            }

            public void setWalletName(String walletName) {
                this.walletName = walletName;
            }

            public double getEtcNum() {
                return etcNum;
            }

            public void setEtcNum(double etcNum) {
                this.etcNum = etcNum;
            }

            public double getJhetcNum() {
                return jhetcNum;
            }

            public void setJhetcNum(double jhetcNum) {
                this.jhetcNum = jhetcNum;
            }

            public double getNoUSDT() {
                return noUSDT;
            }

            public void setNoUSDT(double noUSDT) {
                this.noUSDT = noUSDT;
            }

            public Object getMemberHead() {
                return memberHead;
            }

            public void setMemberHead(Object memberHead) {
                this.memberHead = memberHead;
            }

            public String getMemberPassword() {
                return memberPassword;
            }

            public void setMemberPassword(String memberPassword) {
                this.memberPassword = memberPassword;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getMobileCode() {
                return mobileCode;
            }

            public void setMobileCode(String mobileCode) {
                this.mobileCode = mobileCode;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public Object getBankAccount() {
                return bankAccount;
            }

            public void setBankAccount(Object bankAccount) {
                this.bankAccount = bankAccount;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public Object getAddUserId() {
                return addUserId;
            }

            public void setAddUserId(Object addUserId) {
                this.addUserId = addUserId;
            }

            public String getEditTime() {
                return editTime;
            }

            public void setEditTime(String editTime) {
                this.editTime = editTime;
            }

            public Object getEditUserId() {
                return editUserId;
            }

            public void setEditUserId(Object editUserId) {
                this.editUserId = editUserId;
            }

            public String getMobileActivation() {
                return mobileActivation;
            }

            public void setMobileActivation(String mobileActivation) {
                this.mobileActivation = mobileActivation;
            }

            public double getSyetcNum() {
                return syetcNum;
            }

            public void setSyetcNum(double syetcNum) {
                this.syetcNum = syetcNum;
            }

            public double getUsdtfee() {
                return usdtfee;
            }

            public void setUsdtfee(double usdtfee) {
                this.usdtfee = usdtfee;
            }

            public String getShoubiAddress() {
                return shoubiAddress;
            }

            public void setShoubiAddress(String shoubiAddress) {
                this.shoubiAddress = shoubiAddress;
            }
        }

        public static class DbBean {
            /**
             * memberNumId : 1
             * memberId : 60000
             * daiBiId : 1
             * biprice : 4.0
             * cyNumber : null
             * gerennum : 416638.5
             * isDelete : N
             * addTime : 2019-07-20 23:21:48
             * addUserId : null
             * editTime : null
             * editUserId : null
             * mobile : null
             * nickname : null
             * biname : NMST
             */

            private int memberNumId;
            private int memberId;
            private int daiBiId;
            private double biprice;
            private Object cyNumber;
            private double gerennum;
            private String isDelete;
            private String addTime;
            private Object addUserId;
            private Object editTime;
            private Object editUserId;
            private Object mobile;
            private Object nickname;
            private String biname;
            private String logo;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getMemberNumId() {
                return memberNumId;
            }

            public void setMemberNumId(int memberNumId) {
                this.memberNumId = memberNumId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getDaiBiId() {
                return daiBiId;
            }

            public void setDaiBiId(int daiBiId) {
                this.daiBiId = daiBiId;
            }

            public double getBiprice() {
                return biprice;
            }

            public void setBiprice(double biprice) {
                this.biprice = biprice;
            }

            public Object getCyNumber() {
                return cyNumber;
            }

            public void setCyNumber(Object cyNumber) {
                this.cyNumber = cyNumber;
            }

            public double getGerennum() {
                return gerennum;
            }

            public void setGerennum(double gerennum) {
                this.gerennum = gerennum;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public Object getAddUserId() {
                return addUserId;
            }

            public void setAddUserId(Object addUserId) {
                this.addUserId = addUserId;
            }

            public Object getEditTime() {
                return editTime;
            }

            public void setEditTime(Object editTime) {
                this.editTime = editTime;
            }

            public Object getEditUserId() {
                return editUserId;
            }

            public void setEditUserId(Object editUserId) {
                this.editUserId = editUserId;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public String getBiname() {
                return biname;
            }

            public void setBiname(String biname) {
                this.biname = biname;
            }

    }
}
