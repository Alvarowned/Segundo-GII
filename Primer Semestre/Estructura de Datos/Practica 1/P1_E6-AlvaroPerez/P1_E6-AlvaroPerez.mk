##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=P1_E6-AlvaroPerez
ConfigurationName      :=Debug
WorkspacePath          :="D:/Universidad/2. Curso 2018-2019/Primer Semestre/Estructura de Datos/WorkspaceCPP"
ProjectPath            :="D:/Universidad/2. Curso 2018-2019/Primer Semestre/Estructura de Datos/Practica 1/P1_E6-AlvaroPerez"
IntermediateDirectory  :=./Debug
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=Usuario
Date                   :=25/09/2018
CodeLitePath           :=D:/CodeLite
LinkerName             :=D:/CodeLite+/bin/g++.exe
SharedObjectLinkerName :=D:/CodeLite+/bin/g++.exe -shared -fPIC
ObjectSuffix           :=.o
DependSuffix           :=.o.d
PreprocessSuffix       :=.i
DebugSwitch            :=-g 
IncludeSwitch          :=-I
LibrarySwitch          :=-l
OutputSwitch           :=-o 
LibraryPathSwitch      :=-L
PreprocessorSwitch     :=-D
SourceSwitch           :=-c 
OutputFile             :=$(IntermediateDirectory)/$(ProjectName)
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E
ObjectsFileList        :="P1_E6-AlvaroPerez.txt"
PCHCompileFlags        :=
MakeDirCommand         :=makedir
RcCmpOptions           := 
RcCompilerName         :=D:/CodeLite+/bin/windres.exe
LinkOptions            :=  
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). 
IncludePCH             := 
RcIncludePath          := 
Libs                   := 
ArLibs                 :=  
LibPath                := $(LibraryPathSwitch). 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overriden using an environment variables
##
AR       := D:/CodeLite+/bin/ar.exe rcu
CXX      := D:/CodeLite+/bin/g++.exe
CC       := D:/CodeLite+/bin/gcc.exe
CXXFLAGS :=  -g -O0 -Wall $(Preprocessors)
CFLAGS   :=  -g -O0 -Wall $(Preprocessors)
ASFLAGS  := 
AS       := D:/CodeLite+/bin/as.exe


##
## User defined environment variables
##
CodeLiteDir:=D:\CodeLite
Objects0=$(IntermediateDirectory)/Ej6.cpp$(ObjectSuffix) 



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild MakeIntermediateDirs
all: $(OutputFile)

$(OutputFile): $(IntermediateDirectory)/.d $(Objects) 
	@$(MakeDirCommand) $(@D)
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(LinkerName) $(OutputSwitch)$(OutputFile) @$(ObjectsFileList) $(LibPath) $(Libs) $(LinkOptions)

MakeIntermediateDirs:
	@$(MakeDirCommand) "./Debug"


$(IntermediateDirectory)/.d:
	@$(MakeDirCommand) "./Debug"

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/Ej6.cpp$(ObjectSuffix): Ej6.cpp $(IntermediateDirectory)/Ej6.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "D:/Universidad/2. Curso 2018-2019/Primer Semestre/Estructura de Datos/Practica 1/P1_E6-AlvaroPerez/Ej6.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Ej6.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Ej6.cpp$(DependSuffix): Ej6.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Ej6.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Ej6.cpp$(DependSuffix) -MM Ej6.cpp

$(IntermediateDirectory)/Ej6.cpp$(PreprocessSuffix): Ej6.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Ej6.cpp$(PreprocessSuffix) Ej6.cpp


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) -r ./Debug/


