package com.example.dosa.data.entity;

import java.util.ArrayList;

public class WordDetail {
    private String word;
    private String generalIPA;
    private String usIPA;
    private String ukIPA;
    private ArrayList<Section> sections;
    private ArrayList<String> translations;

    public WordDetail() {
        sections = new ArrayList<>();
        translations = new ArrayList<>();
    }

    public String getWord() {
            return word;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public String getGeneralIPA() {
        return generalIPA;
    }

    public String getUkIPA() {
        return ukIPA;
    }

    public String getUsIPA() {
        return usIPA;
    }

    public ArrayList<String> getTranslations() {
        return translations;
    }

    public void setGeneralIPA(String generalIPA) {
        this.generalIPA = generalIPA;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public void setUkIPA(String ukIPA) {
        this.ukIPA = ukIPA;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setUsIPA(String usIPA) {
        this.usIPA = usIPA;
    }

    public void setTranslations(ArrayList<String> translations) {
        this.translations = translations;
    }

    public static class Section {
        private String pos;
        private ArrayList<DefinitionDetail> definitionDetails;

        public Section(String pos, ArrayList<DefinitionDetail> definitionDetails) {
            this.pos = pos;
            this.definitionDetails = definitionDetails;
        }

        public String getPos() {
            return pos;
        }

        public ArrayList<DefinitionDetail> getDefinitionDetails() {
            return definitionDetails;
        }

        public void setDefinitionDetails(ArrayList<DefinitionDetail> definitionDetails) {
            this.definitionDetails = definitionDetails;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }
    }

    public static class DefinitionDetail {
        private String definition;
        private ArrayList<String> examples;

        public DefinitionDetail(String definition, ArrayList<String> examples) {
            this.definition = definition;
            this.examples = examples;
        }

        public String getDefinition() {
            return definition;
        }

        public ArrayList<String> getExamples() {
            return examples;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public void setExamples(ArrayList<String> examples) {
            this.examples = examples;
        }

    }

}
