# Makefile for common Kubernetes cluster management tasks using kind and kubectl

KIND_CLUSTER_NAME ?= cicd-lab
KIND_CLUSTER_CONFIG ?= kind-config.yaml
KUBECONFIG ?= $(HOME)/.kube/config

.PHONY: create-cluster delete-cluster get-nodes apply-manifest delete-manifest get-pods

create-cluster:
	kind create cluster --name $(KIND_CLUSTER_NAME) --config $(KIND_CLUSTER_CONFIG) --kubeconfig $(KUBECONFIG)

delete-cluster:
	kind delete cluster --name $(KIND_CLUSTER_NAME)

get-nodes:
	kubectl --kubeconfig $(KUBECONFIG) get nodes

# Prmiarily for troubleshooing Jenkins helm chart installation
NAMESPACE ?= jenkins
POD_NAME ?= jenkins-0

get-pods:
	kubectl --kubeconfig $(KUBECONFIG) get pods -n $(NAMESPACE)

get-jenkins-logs:
	kubectl --kubeconfig $(KUBECONFIG) logs -n $(NAMESPACE) $(POD_NAME) -c jenkins

upgrade-jenkins:
	helm upgrade --install jenkins jenkins/jenkins -n $(NAMESPACE) -f .jenkins/values.yaml