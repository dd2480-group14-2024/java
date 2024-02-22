Onboarding:
1. How easily can you build the project? Briefly describe if everything worked as documented or not:
(a) Did you have to install a lot of additional tools to build the software?
We needed maven to clean install, build and run the project, which we have used in previous projects.

(b) Were those tools well documented?
No, it was not well documented. The documentation provided in link shows the application of this jsoniter, rather than building the project.

(c) Were other components installed automatically by the build script?
No, only dependencies of Maven

(d) Did the build conclude automatically without errors?
No. Initially errors appeared regarding a outdated plugin:
groupId: org.apache.maven.plugins
artifactId: maven-compiler-plugin
source & target: 1.6, updated to 1.8

After this update, the build tests failed

(e) How well do examples and tests run on your system(s)?
They give a list of errors in different test classes as listed below:
TestString
TestGson
TestAnnotation
TestAnnotationJsonCreator
TestAnnotationJsonIgnore
TestAnnotationJsonObject
TestAnnotationJsonProperty
TestAnnotationJsonWrapper
TestAnnotationJsonUnwrapper
TestArray
TestDemo
TestExisting
TestGenerics
TestMap
TestNested
TestObject
TestReadAny
TestSpiPropertyDecoder
TestList
TestCollection
TestGenerics

Tests Run: 710, Failures: 5, Errors: 148. Build failed

2. Do you plan to continue or choose another project?
We plan to continue on this project