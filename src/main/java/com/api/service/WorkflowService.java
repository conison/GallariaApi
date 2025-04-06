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