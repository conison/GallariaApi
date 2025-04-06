package com.api.service;

import com.api.model.Workflow;
import com.api.model.WorkflowStatusUpdate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkflowService {

    private final SimpMessagingTemplate template;
    public WorkflowService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void updateWorkflowStatus(String processInstanceId, String taskId, String status) {
        Map<String, String> update = Map.of("taskId", taskId, "status", status);
        template.convertAndSend("/topic/progress/" + processInstanceId, update);
    }
    public List<Workflow> getAllWorkflows() {
        return Arrays.asList(
                new Workflow(1, "Deploy Server", "Running"),
                new Workflow(2, "Update Config", "Completed")
        );
    }

    public void pauseWorkflow(String id) {
        // TODO: Implement actual pause logic
        System.out.println("Paused workflow: " + id);
        updateWorkflowStatus(id, "Task_BackupServer", "inprogress");
    }

    public void resumeWorkflow(String id) {
        // TODO: Implement actual resume logic
        System.out.println("Resumed workflow: " + id);
    }

    public void retryWorkflow(String id) {
        // TODO: Implement actual retry logic
        System.out.println("Retried workflow: " + id);
    }

    public void stopWorkflow(String id) {
        // TODO: Implement actual stop logic
        System.out.println("Stopped workflow: " + id);
    }

    public void killWorkflow(String id) {
        // TODO: Implement actual kill logic
        System.out.println("Killed workflow: " + id);
    }

    public Map<String, Object> getWorkflowWithLogs(String processInstanceId) {
        Map<String, Object> response = new HashMap<>();

      /*  String bpmnXml = """
<?xml version="1.0" encoding="UTF-8"?>
<bpmn2:definitions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xmlns:epwf="http://workflow.ep.gs.com/bpmn"
                   xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL"
                   xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
                   xmlns:dc="http://www.omg.org/spec/DD/20100524/DC"
                   xmlns:di="http://www.omg.org/spec/DD/20100524/DI"
                   id="_joFyELi7EeODZeaR-vkO-Q"
                   targetNamespace="http://activiti.org/bpmn"
                   xsi:schemaLocation="http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd"
                   epwf:modelVersion="1.1.5">

  <bpmn2:process id="com.gs.fi.we.appcentral.fileCopy.approval"
                 name="AppCentral fileCopy approval"
                 isExecutable="true">

    <bpmn2:startEvent id="StartEvent_1">
      <bpmn2:outgoing>Flow_05xo2wu</bpmn2:outgoing>
    </bpmn2:startEvent>

    <bpmn2:sequenceFlow id="Flow_05xo2wu" sourceRef="StartEvent_1" targetRef="Gateway_Ommyuvn"/>

    <bpmn2:exclusiveGateway id="Gateway_Ommyuvn" default="Flow_Oea555q">
      <bpmn2:incoming>Flow_05xo2wu</bpmn2:incoming>
      <bpmn2:outgoing>Flow_1tmk3bm</bpmn2:outgoing>
      <bpmn2:outgoing>Flow_Oea555q</bpmn2:outgoing>
    </bpmn2:exclusiveGateway>

    <bpmn2:userTask id="Activity_1pvxpw8" name="Tech Risk Approval">
      <bpmn2:incoming>Flow_1tmk3bm</bpmn2:incoming>
      <bpmn2:outgoing>Flow_Obcu3zx</bpmn2:outgoing>
    </bpmn2:userTask>

    <bpmn2:serviceTask id="Activity_17f3597" name="Copy package to onshore">
      <bpmn2:incoming>Flow_Obcu3zx</bpmn2:incoming>
      <bpmn2:outgoing>Flow_Ovb84qk</bpmn2:outgoing>
    </bpmn2:serviceTask>

    <bpmn2:endEvent id="EndEvent_1">
      <bpmn2:incoming>Flow_Ovb84qk</bpmn2:incoming>
    </bpmn2:endEvent>

    <bpmn2:sequenceFlow id="Flow_1tmk3bm" sourceRef="Gateway_Ommyuvn" targetRef="Activity_1pvxpw8"/>
    <bpmn2:sequenceFlow id="Flow_Obcu3zx" sourceRef="Activity_1pvxpw8" targetRef="Activity_17f3597"/>
    <bpmn2:sequenceFlow id="Flow_Ovb84qk" sourceRef="Activity_17f3597" targetRef="EndEvent_1"/>
    <bpmn2:sequenceFlow id="Flow_Oea555q" sourceRef="Gateway_Ommyuvn" targetRef="EndEvent_1"/>

  </bpmn2:process>

  <bpmndi:BPMNDiagram id="BPMNDiagram_1">
    <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="com.gs.fi.we.appcentral.fileCopy.approval">

      <bpmndi:BPMNShape id="StartEvent_1_di" bpmnElement="StartEvent_1">
        <dc:Bounds x="100" y="180" width="36" height="36"/>
      </bpmndi:BPMNShape>

      <bpmndi:BPMNShape id="Gateway_Ommyuvn_di" bpmnElement="Gateway_Ommyuvn" isMarkerVisible="true">
        <dc:Bounds x="160" y="175" width="50" height="50"/>
      </bpmndi:BPMNShape>

      <bpmndi:BPMNShape id="Activity_1pvxpw8_di" bpmnElement="Activity_1pvxpw8">
        <dc:Bounds x="240" y="160" width="120" height="80"/>
      </bpmndi:BPMNShape>

      <bpmndi:BPMNShape id="Activity_17f3597_di" bpmnElement="Activity_17f3597">
        <dc:Bounds x="390" y="160" width="160" height="80"/>
      </bpmndi:BPMNShape>

      <bpmndi:BPMNShape id="EndEvent_1_di" bpmnElement="EndEvent_1">
        <dc:Bounds x="580" y="180" width="36" height="36"/>
      </bpmndi:BPMNShape>

      <bpmndi:BPMNEdge id="Flow_05xo2wu_di" bpmnElement="Flow_05xo2wu">
        <di:waypoint x="136" y="198"/>
        <di:waypoint x="160" y="198"/>
      </bpmndi:BPMNEdge>

      <bpmndi:BPMNEdge id="Flow_1tmk3bm_di" bpmnElement="Flow_1tmk3bm">
        <di:waypoint x="210" y="198"/>
        <di:waypoint x="240" y="198"/>
      </bpmndi:BPMNEdge>

      <bpmndi:BPMNEdge id="Flow_Obcu3zx_di" bpmnElement="Flow_Obcu3zx">
        <di:waypoint x="360" y="198"/>
        <di:waypoint x="390" y="198"/>
      </bpmndi:BPMNEdge>

      <bpmndi:BPMNEdge id="Flow_Ovb84qk_di" bpmnElement="Flow_Ovb84qk">
        <di:waypoint x="550" y="198"/>
        <di:waypoint x="580" y="198"/>
      </bpmndi:BPMNEdge>

      <bpmndi:BPMNEdge id="Flow_Oea555q_di" bpmnElement="Flow_Oea555q">
        <di:waypoint x="185" y="225"/>
        <di:waypoint x="580" y="225"/>
        <di:waypoint x="580" y="198"/>
      </bpmndi:BPMNEdge>

    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</bpmn2:definitions>
"""; */


        String bpmnXml = """
<?xml version="1.0" encoding="UTF-8"?>
<bpmn:definitions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL"
                  xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
                  xmlns:dc="http://www.omg.org/spec/DD/20100524/DC"
                  xmlns:di="http://www.omg.org/spec/DD/20100524/DI"
                  id="Definitions_1"
                  targetNamespace="http://bpmn.io/schema/bpmn">

  <bpmn:process id="Process_1" isExecutable="true">
    <bpmn:startEvent id="StartEvent_1" name="Start"/>
    <bpmn:task id="Task_ApproveUser" name="Approve User"/>
    <bpmn:task id="Task_ValidateEmail" name="Validate Email"/>
    <bpmn:exclusiveGateway id="Gateway_ValidationResult" name="Email Valid?"/>
    <bpmn:task id="Task_BackupServer" name="Backup Server"/>
    <bpmn:task id="Task_SecurityScan" name="Security Scan"/>
    <bpmn:task id="Task_UpdateDNS" name="Update DNS"/>
    <bpmn:task id="Task_NotifyAdmin" name="Notify Admin"/>
    <bpmn:task id="Task_PerformAudit" name="Perform Audit"/>
    <bpmn:task id="Task_RestartService" name="Restart Service"/>
    <bpmn:task id="Task_FinalizeDeployment" name="Finalize Deployment"/>
    <bpmn:endEvent id="EndEvent_1" name="End"/>

    <bpmn:sequenceFlow id="Flow1" sourceRef="StartEvent_1" targetRef="Task_ApproveUser"/>
    <bpmn:sequenceFlow id="Flow2" sourceRef="Task_ApproveUser" targetRef="Task_ValidateEmail"/>
    <bpmn:sequenceFlow id="Flow3" sourceRef="Task_ValidateEmail" targetRef="Gateway_ValidationResult"/>
    <bpmn:sequenceFlow id="Flow4a" sourceRef="Gateway_ValidationResult" targetRef="Task_BackupServer">
      <bpmn:conditionExpression xsi:type="bpmn:tFormalExpression"><![CDATA[${emailValid}]]></bpmn:conditionExpression>
    </bpmn:sequenceFlow>
    <bpmn:sequenceFlow id="Flow4b" sourceRef="Gateway_ValidationResult" targetRef="Task_SecurityScan">
      <bpmn:conditionExpression xsi:type="bpmn:tFormalExpression"><![CDATA[${!emailValid}]]></bpmn:conditionExpression>
    </bpmn:sequenceFlow>
    <bpmn:sequenceFlow id="Flow5" sourceRef="Task_BackupServer" targetRef="Task_UpdateDNS"/>
    <bpmn:sequenceFlow id="Flow6" sourceRef="Task_SecurityScan" targetRef="Task_NotifyAdmin"/>
    <bpmn:sequenceFlow id="Flow7" sourceRef="Task_UpdateDNS" targetRef="Task_PerformAudit"/>
    <bpmn:sequenceFlow id="Flow8" sourceRef="Task_NotifyAdmin" targetRef="Task_PerformAudit"/>
    <bpmn:sequenceFlow id="Flow9" sourceRef="Task_PerformAudit" targetRef="Task_RestartService"/>
    <bpmn:sequenceFlow id="Flow10" sourceRef="Task_RestartService" targetRef="Task_FinalizeDeployment"/>
    <bpmn:sequenceFlow id="Flow11" sourceRef="Task_FinalizeDeployment" targetRef="EndEvent_1"/>
  </bpmn:process>

  <bpmndi:BPMNDiagram id="BPMNDiagram_1">
    <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_1">
      <bpmndi:BPMNShape id="StartEvent_1_di" bpmnElement="StartEvent_1">
        <dc:Bounds x="100" y="120" width="36" height="36"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_ApproveUser_di" bpmnElement="Task_ApproveUser">
        <dc:Bounds x="180" y="110" width="100" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_ValidateEmail_di" bpmnElement="Task_ValidateEmail">
        <dc:Bounds x="300" y="110" width="100" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Gateway_ValidationResult_di" bpmnElement="Gateway_ValidationResult" isMarkerVisible="true">
        <dc:Bounds x="430" y="115" width="50" height="50"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_BackupServer_di" bpmnElement="Task_BackupServer">
        <dc:Bounds x="520" y="50" width="120" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_SecurityScan_di" bpmnElement="Task_SecurityScan">
        <dc:Bounds x="520" y="180" width="120" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_UpdateDNS_di" bpmnElement="Task_UpdateDNS">
        <dc:Bounds x="680" y="50" width="100" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_NotifyAdmin_di" bpmnElement="Task_NotifyAdmin">
        <dc:Bounds x="680" y="180" width="100" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_PerformAudit_di" bpmnElement="Task_PerformAudit">
        <dc:Bounds x="820" y="115" width="120" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_RestartService_di" bpmnElement="Task_RestartService">
        <dc:Bounds x="970" y="115" width="120" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Task_FinalizeDeployment_di" bpmnElement="Task_FinalizeDeployment">
        <dc:Bounds x="1120" y="115" width="120" height="56"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="EndEvent_1_di" bpmnElement="EndEvent_1">
        <dc:Bounds x="1270" y="120" width="36" height="36"/>
      </bpmndi:BPMNShape>

      <!-- Sequence Flows -->
      <bpmndi:BPMNEdge id="Flow1_di" bpmnElement="Flow1">
        <di:waypoint x="136" y="138"/>
        <di:waypoint x="180" y="138"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow2_di" bpmnElement="Flow2">
        <di:waypoint x="280" y="138"/>
        <di:waypoint x="300" y="138"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow3_di" bpmnElement="Flow3">
        <di:waypoint x="400" y="138"/>
        <di:waypoint x="430" y="138"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow4a_di" bpmnElement="Flow4a">
        <di:waypoint x="480" y="138"/>
        <di:waypoint x="520" y="78"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow4b_di" bpmnElement="Flow4b">
        <di:waypoint x="480" y="138"/>
        <di:waypoint x="520" y="208"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow5_di" bpmnElement="Flow5">
        <di:waypoint x="640" y="78"/>
        <di:waypoint x="680" y="78"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow6_di" bpmnElement="Flow6">
        <di:waypoint x="640" y="208"/>
        <di:waypoint x="680" y="208"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow7_di" bpmnElement="Flow7">
        <di:waypoint x="780" y="78"/>
        <di:waypoint x="820" y="138"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow8_di" bpmnElement="Flow8">
        <di:waypoint x="780" y="208"/>
        <di:waypoint x="820" y="138"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow9_di" bpmnElement="Flow9">
        <di:waypoint x="940" y="138"/>
        <di:waypoint x="970" y="138"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow10_di" bpmnElement="Flow10">
        <di:waypoint x="1090" y="138"/>
        <di:waypoint x="1120" y="138"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Flow11_di" bpmnElement="Flow11">
        <di:waypoint x="1240" y="138"/>
        <di:waypoint x="1270" y="138"/>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</bpmn:definitions>
""";




        response.put("bpmnXml", bpmnXml);
        return response;
    }

}