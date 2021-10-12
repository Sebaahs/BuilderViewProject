package xyz.testeos.builderviewalpha03.src.usecases.common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import xyz.testeos.builderviewalpha03.src.model.domain.Build;
import xyz.testeos.builderviewalpha03.src.model.domain.Calculation;
import xyz.testeos.builderviewalpha03.src.model.domain.Material;

public class SharedViewModel extends ViewModel {

    //Definicion -> MutableLiveData construccion seleccionada
    private MutableLiveData<Build> selectedBuild = new MutableLiveData<>();
    //Definicion -> MutableLiveData lista completa
    private MutableLiveData<List<Build>> completeList = new MutableLiveData<>();
    //Definicion -> calculationList
    List<Calculation> calculationList;
    private float total;

    //Get
    public LiveData<Build> getSelectedBuild() {
        return selectedBuild;
    }

    public LiveData<List<Build>> getCompleteList() {
        return completeList;
    }

    public float getTotal() { return total; }

    //set
    public void setSelectedBuild(Build data) {
        selectedBuild.setValue(data);
    }

    public void setCompleteList(List<Build> data) {
        completeList.setValue(data);
    }


    public List<Calculation> makeCalculation(List<Material> materialsData, List<Build> completeData) {

        total = 0;

        initCalculation(materialsData);

        for (Build item : completeData) {

            if (item.getNameBuild().equals("Pared")) {
                if (item.getParamType().equals("0")) {
                    insertAmount("ladrillo hueco 8cm", 16 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cemento", (float) 1.04 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cal", (float) 1.98 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena", (float) (0.01 * Float.parseFloat(item.getParamExt())));
                }
                if (item.getParamType().equals("1")) {
                    insertAmount("ladrillo hueco 12cm", 16 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cemento", (float) 1.55 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cal", (float) 2.97 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena", (float) (0.015 * Float.parseFloat(item.getParamExt())));

                }
                if (item.getParamType().equals("2")) {

                    insertAmount("ladrillo 15cm", 61 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cemento", (float) 3.5 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cal", (float) 8.27 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena", (float) (0.04 * Float.parseFloat(item.getParamExt())));
                }
                if (item.getParamType().equals("3")) {
                    insertAmount("ladrillo hueco 18cm", 16 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cemento", (float) 2.33 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cal", (float) 4.45 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena", (float) (0.022 * Float.parseFloat(item.getParamExt())));
                }
            }
            if (item.getNameBuild().equals("Encadenado / Viga") || item.getNameBuild().equals("Columna")) {
                if (item.getParamType().equals("0")){
                    insertAmount("cemento",13 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena",(float) 0.02 * Float.parseFloat(item.getParamExt()));
                    insertAmount("piedra partida",(float) 0.03 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 10mm",4 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 4,2mm",4 * Float.parseFloat(item.getParamExt()));
                }
                if (item.getParamType().equals("1")){

                    insertAmount("cemento",13 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena",(float) 0.02 * Float.parseFloat(item.getParamExt()));
                    insertAmount("piedra partida",(float) 0.03 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 8mm",4 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 4,2mm",4 * Float.parseFloat(item.getParamExt()));

                }
            }
            if (item.getNameBuild().equals("Zapata")) {
                if (item.getParamType().equals("0")){
                    insertAmount("cemento",(float) 58.41 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena",(float) 0.125 * Float.parseFloat(item.getParamExt()));
                    insertAmount("piedra partida",(float) 0.125 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 8mm",18 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 4,2mm",11 * Float.parseFloat(item.getParamExt()));
                }
                if (item.getParamType().equals("1")){
                    insertAmount("cemento",(float) 58.41 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena",(float) 0.125 * Float.parseFloat(item.getParamExt()));
                    insertAmount("piedra partida",(float) 0.125 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 10mm",18 * Float.parseFloat(item.getParamExt()));
                    insertAmount("hierro 4,2mm",11 * Float.parseFloat(item.getParamExt()));

                }
            }
            if (item.getNameBuild().equals("Piso")) {
                if (item.getParamType().equals("0")){
                    insertAmount("ceramica 25x25cm",(float) 16 * Float.parseFloat(item.getParamExt()));
                }
                if (item.getParamType().equals("1")){
                    insertAmount("ceramica 40x40cm",(float) 6.25 * Float.parseFloat(item.getParamExt()));
                }
                if (item.getParamType().equals("2")){
                    insertAmount("porcelanato 60x60cm",(float) 2.68  * Float.parseFloat(item.getParamExt()));
                }
                if (item.getParamType().equals("3")){
                    insertAmount("porcelanato 60x120cm",(float) 1.39 * Float.parseFloat(item.getParamExt()));
                }
            }
            if (item.getNameBuild().equals("Techo de teja")) {
                insertAmount("Teja francesa",(float) 13.8 * Float.parseFloat(item.getParamExt()));
            }
            if (item.getNameBuild().equals("Techo de chapa")) {
                if (item.getParamType().equals("0")){
                    insertAmount("chapa acanalada",Float.parseFloat(item.getParamExt()));
                }
                if (item.getParamType().equals("1")){
                    insertAmount("chapa trapezoidal",Float.parseFloat(item.getParamExt()));
                }
            }
            if (item.getNameBuild().equals("Revoque")) {
                if (item.getParamType().equals("0")){
                    insertAmount("cemento", (float) 2 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cal", (float) 3 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena", (float) (0.02 * Float.parseFloat(item.getParamExt())));
                }
                if (item.getParamType().equals("1")){
                    insertAmount("cemento", (float) 0.5 * Float.parseFloat(item.getParamExt()));
                    insertAmount("cal", (float) 1.5 * Float.parseFloat(item.getParamExt()));
                    insertAmount("arena", (float) (0.01 * Float.parseFloat(item.getParamExt())));
                }
            }
        }

        makeCost(materialsData, calculationList);

        removeVoids();

        return calculationList;
    }

    public void insertAmount(String name, float amount) {
        for (Calculation item : calculationList) {
            if (item.getName().equals(name)) {
                item.addAmount(amount);
                break;
            }
        }
    }

    //Set de nombres de la api a la "Lista de Computo"
    private void initCalculation(List<Material> materialsData) {
        calculationList = new ArrayList<>();

        for (int i = 0; i < materialsData.size(); i++) {
            calculationList.add(new Calculation(materialsData.get(i).getMaterial()));
        }
    }


    private void removeVoids() {
        for (int i = 0; i < calculationList.size(); i++){
            if (calculationList.get(i).getAmount() == 0){
                calculationList.remove(i);
                i--;
            }
        }
    }


    //set de precios de la api a la "Lista de Computo"
    private void makeCost(List<Material> materialsData, List<Calculation> calculationList) {

        for (int i = 0; i < materialsData.size(); i++) {
            if (materialsData.get(i).getMaterial().equals(calculationList.get(i).getName())) {
                calculationList.get(i).setCost(calculationList.get(i).getAmount() * materialsData.get(i).getAverage());
                total += calculationList.get(i).getAmount() * materialsData.get(i).getAverage();
            }
        }

    }
}