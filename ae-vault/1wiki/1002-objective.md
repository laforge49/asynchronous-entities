from [1000-wiki](1000-wiki.md)
# 1002 Objective
For the past few years I've worked on a single-threaded program with all state collected in a tree built using persistent maps with something under a million name/value pairs. It feels wrong, especially if I want to support swing or multi-threading in general. What I'd like to do is to create a tree of [1003-entities](1003-entities.md). 