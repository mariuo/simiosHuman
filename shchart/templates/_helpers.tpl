{{/* Define a helper to generate the full MySQL connection URL */}}
{{- define "my-spring-app.fullMyPostgresConnectionURL" -}}
jdbc:postgresql://{{ .Release.Name }}-postgres-service:5432/{{ .Values.postgres.databaseName }}?createDatabaseIfNotExist=true
{{- end -}}