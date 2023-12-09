#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <day_number>"
    exit 1
fi

day_number=$1
day_folder="main/java/day_$day_number"
resources_folder="main/resources"

if [ -d "$day_folder" ]; then
    echo "Erreur : Le dossier day_$day_number existe déjà."
    exit 1
fi

mkdir -p "$day_folder"

touch "$day_folder/Part1.java"
touch "$day_folder/Part2.java"

echo "package day_$day_number;

import common.ReadFile;

import java.io.IOException;

public class Part1 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read(\"tmp.txt\");
//            input = ReadFile.read(\"day_$day_number.txt\");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}" > "$day_folder/Part1.java"

echo "package day_$day_number;

import common.ReadFile;

import java.io.IOException;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read(\"tmp.txt\");
//            input = ReadFile.read(\"day_$day_number.txt\");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}" > "$day_folder/Part2.java"

mkdir -p "$resources_folder"

touch "$resources_folder/day_$day_number.txt"

git add "$day_folder/Part1.java" "$day_folder/Part2.java" "$resources_folder/day_$day_number.txt"


echo "Projet initialisé avec succès pour le jour $day_number."