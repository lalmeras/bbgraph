package org.likide.bbgraph.init.model;
// Generated 12 avr. 2020 22:05:12 by Hibernate Tools 5.4.13.Final



/**
 * GraphGraph generated by hbm2java
 */
public class GraphGraph  implements java.io.Serializable {


     private int id;
     private String name;
     private String jps;
     private int template;
     private String savedOn;

    public GraphGraph() {
    }

    public GraphGraph(int id, String name, String jps, int template, String savedOn) {
       this.id = id;
       this.name = name;
       this.jps = jps;
       this.template = template;
       this.savedOn = savedOn;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getJps() {
        return this.jps;
    }
    
    public void setJps(String jps) {
        this.jps = jps;
    }
    public int getTemplate() {
        return this.template;
    }
    
    public void setTemplate(int template) {
        this.template = template;
    }
    public String getSavedOn() {
        return this.savedOn;
    }
    
    public void setSavedOn(String savedOn) {
        this.savedOn = savedOn;
    }




}


