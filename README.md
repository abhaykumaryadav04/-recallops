# RecallOps 🧠

> AI-powered incident response backend with persistent semantic memory.

RecallOps is a backend system designed to help engineering teams record, resolve, and learn from production incidents.

Instead of relying only on keyword-based searches, RecallOps uses **OpenAI embeddings + PostgreSQL pgvector** to find previously resolved incidents that are semantically similar to a new problem.

---

## 🚀 Problem

When a production incident occurs, engineers often spend significant time investigating problems that may have already been solved in the past.

Traditional keyword search has limitations:

```text
"Authentication endpoint forbidden"
