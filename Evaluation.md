Onboarding:
1. How easily can you build the project? Briefly describe if everything worked as documented or not:
(a) Did you have to install a lot of additional tools to build the software?
We needed maven to build the project, which we have used in previous projects.

(b) Were those tools well documented?
No, it was not well documented, as it was not stated

(c) Were other components installed automatically by the build script?
No

(d) Did the build conclude automatically without errors?
No. Initially errors appeared regarding a outdated plugin:
groupId: org.apache.maven.plugins
artifactId: maven-compiler-plugin
version: 3.7.0, which was updated to version: 3.8.1
source & target: 1.6, updated to 1.8

After this update, the build tests failed

(e) How well do examples and tests run on your system(s)?
They give a list of errors

2. Do you plan to continue or choose another project?
We plan to continue on this project