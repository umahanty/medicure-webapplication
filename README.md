## Certification Project – Medicure


### Project Overview

**Medicure** is a super specialty hospital based in New York, USA, providing world-class treatment and surgery, including Heart, Liver, Kidney transplants, and the first robotic surgery center. The chain is owned and managed by Global Health Limited.

Medicure aims to centrally manage all doctors' and patients' data across its hospitals in various cities. They have developed a microservice to offer these services. To reduce unnecessary maintenance costs and manual labor, they aim to automate their application build and deployment process using DevOps. They are open to using any one of the (AWS, Azure, GCP) cloud platforms as their primary cloud service provider.

### Goals

- Deliver product updates frequently to production with high quality and reliability.
- Accelerate software delivery speed and quality.
- Reduce feedback time between developers and testers.
- Use Kubernetes to manage container deployments, scaling, and descaling.

### Current Challenges

- Building complex builds is difficult.
- Manual efforts to test various components/modules of the project.
- Incremental builds are difficult to manage, test, and deploy.
- Creation of infrastructure and configuration is very time-consuming.
- Continuous manual monitoring of the application is challenging.

### Solution Implementation

Develop a Mavenized microservice using Spring Boot and an in-memory H2 database.

#### Microservice Requirements

1. **Endpoints:**
   - **/registerDoctor** (HTTP Method: POST)  
     Request Body: JSON
   - **/updateDoctor/{doctorRegNo}** (HTTP Method: PUT)  
     Request Body: JSON
   - **/searchDoctor/{doctorName}** (HTTP Method: GET)  
     No Request Body
   - **/deleteDoctor/{doctorRegNo}** (HTTP Method: DELETE)  
     No Request Body

2. Write necessary JUnit test cases.
3. Generate HTML report using TestNG.
4. Push code into your GitHub Repository.
   
   **Note:** Preload some data into the database.

### Continuous Integration & Continuous Deployment Tools

- **Git**: For version control.
- **Jenkins**: For continuous integration and deployment.
- **Docker**: For containerizing applications.
- **Ansible**: Configuration management tool.
- **Selenium**: For automating tests on the deployed web application.
- **Terraform**: For infrastructure creation.
- **Kubernetes**: For running containerized applications in a managed cluster.

### Business Challenge/Requirement

When a developer pushes the updated code to the GIT master branch:
1. The Jenkins pipeline should be triggered.
2. Code should be checked out, compiled, tested, packaged, and containerized.
3. A new EKS test-cluster should be provisioned and configured automatically with all the required software.
4. Once the cluster is healthy and available, the application must be deployed to the test-server automatically using Kubernetes.
5. The deployment should be tested using a test automation tool.

This process should be automatic and triggered by a push to the GitHub master branch.

### Kubernetes Cluster Requirements

- Must contain at least 2 servers.
- Must be continuously monitored using Prometheus.
- Dashboard must be visualized using Grafana.

## Application source code is available at the following link
https://github.com/mentorbabaa/medicure-app.git

## How to submit the project the project : 

1. Create a document with detailed step by step tasks along with the screenshot. 
2. Please mention your github repository containing all the codes includeing application code, pipeline code, Dockerfile, Ansible scripts, Terraform scripts, kubernetes scripts etc.
3. Upload the project in your Designated Google Drive Folder.
