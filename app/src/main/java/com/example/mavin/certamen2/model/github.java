package com.example.mavin.certamen2.model;

/**
 * Created by mavin on 30/9/2016.
 */

public class github {

        private String name;
        private String description;
        private String updated_at;
        private String html_url;

        public void setName(String nombre){
            this.name = nombre;
        }

        public String getName(){
            return name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String genero) {
            this.updated_at = genero;
        }

        public String getHtml_url() {
        return html_url;
    }

        public void setHtml_url(String genero) {
        this.html_url = html_url;
    }


}
