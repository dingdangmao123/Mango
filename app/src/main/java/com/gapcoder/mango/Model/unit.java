package com.gapcoder.mango.Model;

import java.util.List;

/**
 * Created by suxiaohui on 2018/1/31.
 */

public class unit {


    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101230201","location":"厦门","parent_city":"厦门","admin_area":"福建","cnty":"中国","lat":"24.4904747","lon":"118.11022186","tz":"+8.0"}
         * update : {"loc":"2018-01-31 18:03","utc":"2018-01-31 10:03"}
         * status : ok
         * daily_forecast : [{"cond_code_d":"305","cond_code_n":"104","cond_txt_d":"小雨","cond_txt_n":"阴","date":"2018-01-31","hum":"74","mr":"17:40","ms":"06:18","pcpn":"4.3","pop":"97","pres":"1023","sr":"06:49","ss":"17:52","tmp_max":"11","tmp_min":"8","uv_index":"1","vis":"10","wind_deg":"0","wind_dir":"无持续风向","wind_sc":"微风","wind_spd":"3"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2018-02-01","hum":"63","mr":"18:45","ms":"07:13","pcpn":"0.0","pop":"0","pres":"1024","sr":"06:49","ss":"17:53","tmp_max":"13","tmp_min":"7","uv_index":"7","vis":"19","wind_deg":"71","wind_dir":"东北风","wind_sc":"3-4","wind_spd":"17"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2018-02-02","hum":"61","mr":"19:50","ms":"08:03","pcpn":"0.0","pop":"0","pres":"1026","sr":"06:48","ss":"17:54","tmp_max":"16","tmp_min":"8","uv_index":"7","vis":"20","wind_deg":"0","wind_dir":"无持续风向","wind_sc":"微风","wind_spd":"4"}]
         * sunrise_sunset : [{"$ref":"$[0].daily_forecast[0]"},{"$ref":"$[0].daily_forecast[1]"},{"$ref":"$[0].daily_forecast[2]"}]
         */
        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private List<DailyForecastBean> daily_forecast;
        private List<SunriseSunsetBean> sunrise_sunset;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<SunriseSunsetBean> getSunrise_sunset() {
            return sunrise_sunset;
        }

        public void setSunrise_sunset(List<SunriseSunsetBean> sunrise_sunset) {
            this.sunrise_sunset = sunrise_sunset;
        }

        public static class BasicBean {
            /**
             * cid : CN101230201
             * location : 厦门
             * parent_city : 厦门
             * admin_area : 福建
             * cnty : 中国
             * lat : 24.4904747
             * lon : 118.11022186
             * tz : +8.0
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2018-01-31 18:03
             * utc : 2018-01-31 10:03
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class DailyForecastBean {
            /**
             * cond_code_d : 305
             * cond_code_n : 104
             * cond_txt_d : 小雨
             * cond_txt_n : 阴
             * date : 2018-01-31
             * hum : 74
             * mr : 17:40
             * ms : 06:18
             * pcpn : 4.3
             * pop : 97
             * pres : 1023
             * sr : 06:49
             * ss : 17:52
             * tmp_max : 11
             * tmp_min : 8
             * uv_index : 1
             * vis : 10
             * wind_deg : 0
             * wind_dir : 无持续风向
             * wind_sc : 微风
             * wind_spd : 3
             */

            private String cond_code_d;
            private String cond_code_n;
            private String cond_txt_d;
            private String cond_txt_n;
            private String date;
            private String hum;
            private String mr;
            private String ms;
            private String pcpn;
            private String pop;
            private String pres;
            private String sr;
            private String ss;
            private String tmp_max;
            private String tmp_min;
            private String uv_index;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(String cond_code_d) {
                this.cond_code_d = cond_code_d;
            }

            public String getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(String cond_code_n) {
                this.cond_code_n = cond_code_n;
            }

            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class SunriseSunsetBean {
            /**
             * $ref : $[0].daily_forecast[0]
             */

            private String $ref;

            public String get$ref() {
                return $ref;
            }

            public void set$ref(String $ref) {
                this.$ref = $ref;
            }
        }
    }
}
