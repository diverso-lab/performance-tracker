# Performance Tracker

## Description

This is a software tool developed to help software teams measure their performance. This measurement is based on four different metrics: Deployment Frequency, Lead Time For Changes, Time To Restore Service and Change Failure Rate. These metrics can be applied to projects located in GitHub using the tool.

This project is part of a research focused on eploring the the impact of DevOps practices on open source software projects. The projects used in the study are Angular, Kubernetes, TensorFlow and Visual Studio Code

## Running the application

In order to run the application, you must download it from this repository and follow these steps:

1. We need to decide the period which we want to collect data from. In the .env file, we will specify the starting date in "SINCE_DAY" and the ending day of the period in "UNTIL_DAY". The following example shows the appropiate format: 2022-06-01T00:00:00+00:00

2. We will need a GitHub account to generate a personal access token. If you want to know more about how to create a personal access token and why are they useful, please visit the following website: https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token . The generated token will be specified in "GITHUB_PASSWORD" and our GitHub username in "GITHUB_USER".

3. "GITHUB_OWNER" and "GITHUB_REPOSITORY" will define which repository we want to access. We will also assign a value to "BUG_LABEL", which defines the name of the label used in the repository to identify bugs (this value is used to in the Time To Restore Service and Change Failure Rate metrics). For the projects studied in the research, these are the values used:

| Project            | GITHUB_OWNER | GITHUB_REPOSITORY | BUG_LABEL |
| ------------------ | ------------ | ----------------- | --------- |
| Angular            | angular      | angular           | bug       |
| Kubernetes         | kubernetes   | kubernetes        | kind/bug  |
| TensorFlow         | tensorflow   | tensorflow        | type:bug  |
| Visual Studio Code | microsoft    | vscode            | bug       |

4. Once all of the .env variables are set, we will run the following command:

```
./mvnw quarkus:dev
```

The process of measuring the data make take up to 40 minutes.

This project uses Quarkus, the Supersonic Subatomic Java Framework. If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .