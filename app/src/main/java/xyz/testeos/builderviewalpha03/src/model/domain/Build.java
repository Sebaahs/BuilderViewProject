package xyz.testeos.builderviewalpha03.src.model.domain;

public class Build {

    // model class para las construcciones


    //Definicion
    private String nameBuild;
    private int imgBuild;
    private String unit;
    private int paramType;
    private float paramExt;

    //Constructor --> Construcciones
    public Build(String nameBuild, int imgBuild, String unit) {
        this.nameBuild = nameBuild;
        this.imgBuild = imgBuild;
        this.unit = unit;
    }
    //Constructor --> Listado
    public Build(String nameBuild, int imgBuild, String unit, int paramType, float paramExt) {
        this.nameBuild = nameBuild;
        this.imgBuild = imgBuild;
        this.unit = unit;
        this.paramType = paramType;
        this.paramExt = paramExt;
    }

    //Empty Constructor
    public Build(){    }

    //Getters
    public String getNameBuild() {
        return nameBuild;
    }
    public int getImgBuild() {
        return imgBuild;
    }
    public String getParamType() { return String.valueOf(paramType); }
    public String getParamExt() {
        return String.valueOf(paramExt);
    }
    public String getUnit(){ return unit; }

    //Setters
    public void setParamType(int paramType) { this.paramType = paramType; }
    public void setParamExt(int paramExt) { this.paramExt = paramExt; }
}
