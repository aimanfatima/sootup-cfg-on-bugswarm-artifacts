# Generating Control Flow Graphs using Soot

## To Generate CFG - 

```
mvn clean compile 
mvn exec:java -Dexec.mainClass="com.aiman.cfggenerator.CFGGenerator"
```

DOT file for that particular methodName will be created in the output/dotfiles folder

## To change the sample program - 

1. Add a new Class in - `src/main/java/com/aiman/examples`
2. Compile the class, so that the class file is available in the target directory
3. Update `clsName` and `methodName` in `CFGGenerator` class 
4. Run the above commands