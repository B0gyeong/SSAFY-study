# 🛠 Java 파일 컴파일 방법

1. Java 코드 컴파일하기
- javac ClassName.java
- javac ClassName.java -encoding utf-8

2. input 파일로 실행시키기
- java ClassName < input_파일로_가는_상대경로_그리고_파일명
- ex) java Solution < ../input/inputCalc.txt

3. 번외) input 파일에서 받아서 output 파일로 결과 받기
- java ClassName < input_파일로_가는_상대경로_그리고_파일명 > output_파일로_가는_상대경로_파일명
- ex) java Solution < ../input/inputCalc.txt > ./outputCalc.txt