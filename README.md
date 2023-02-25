# f29oc-2022-23-concurrency-cw1 #

Code stubs for coursework

You should 
1. **fork** this project into your own private remote namespace using the GitLab browser interface from [this project](https://gitlab-student.macs.hw.ac.uk/f29oc-2022-23/f29oc-2022-23-students/f29oc-2022-23-students-coursework/f29oc-2022-23-concurrency-cw1).
2. **clone** your remote project to a local copy on your computer (e.g. using Eclipse). 
3. Develop your solution *incrementally* by editing your local (clone) copy and committing and pushing your changes regularly back up to your forked, remote, remote copy in your private GitLab namespace.
4. **You must not add any other files to this project.** 

Note:
If you already have a project named "f29oc-2022-23-concurrency-cw1" in Eclipse, then cloning this into Eclipse will create a local copy, but confusingly it will not show in the Eclipse package explorer! The easiest, although least satisfactory way to deal with this is to *file > switch workspace* to a workspace that does not contain a project with this name.

Running this will report failures as DrillLoginManager class only contains code stubs:

```java
ExampleTest_A
4 Roustabout threads started
teamRequest = {Roustabout=2}
Number of Roustabouts released by manager = 5
Hence: FAIL

ExampleTest_B
Error: teamName returned by worker thread Thread-5(Roustabout) = null, is incorrect (it should be TeamX)
Error: teamName returned by worker thread Thread-6(Roustabout) = null, is incorrect (it should be TeamX)
Error: teamName returned by worker thread Thread-7(Roustabout) = null, is incorrect (it should be TeamX)
3 Roustabout threads started
1 Driller thread started by calling: 'drillLoginManager.drillerRequest', teamRequest = {Driller=1, Roustabout=2}, teamName = TeamX
Number of Roustabouts released to run by drillLoginManager for team TeamX = 0
Number of Driller threads released to run by drillLoginManager = 1
Hence: FAIL
```