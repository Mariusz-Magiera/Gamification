using System;
using System.Collections.Generic;

[Serializable]
public class Project
{
    public int id;
    public string name;
    public string description;
    public List<Task> tasks;
    public List<Achievement> achievements;
}


[Serializable]
public class Projects
{
    public List<Project> List = new List<Project>();
}