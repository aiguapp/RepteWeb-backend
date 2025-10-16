# 💧 AiguaApp
### Gestió sostenible de l’aigua a Catalunya – Hackathon 2025

---

## 🌍 Context

**AiguaApp** és una aplicació web orientada a la **monitorització i conscienciació sobre el consum d’aigua** als barris de Catalunya.  
Forma part del **Repte 3 – Gestió sostenible dels recursos hídrics**, alineat amb els **ODS 6 (Aigua neta i sanejament)** i **ODS 13 (Acció pel clima)**.

> *“L’aigua també té un ritme.”*  
> L’aplicació mostra el ritme de consum per barri i detecta quan aquest ritme es veu alterat.

---

## ⚙️ Arquitectura general

| Capa | Tecnologia | Descripció |
|------|-------------|------------|
| **Frontend** | React + Leaflet | Visualització de dades (mapa, gràfics, alertes) |
| **Backend** | Spring Boot (Java 17) | API REST que exposa les dades de consum i anomalies |
| **Data Layer** | JSON simulat | Simulació de dades per barris |
| **Service Layer** | Lògica de negoci | Gestió de lectura de fitxers i codis HTTP |
| **Controller Layer** | Endpoints REST | Exposa `/consumption/`, `/consumption/summary`, `/anomalies/` |

---

## 🧱 Estructura del backend

```
src/main/java/com/aiguaapp/aiguaapp/
├── AiguappApplication.java
├── controller
│   ├── AnomaliesController.java
│   └── ConsumtionCotroller.java
└── service
    └── FileDataService.java
```

---

## 📊 Endpoints REST

| Mètode | Endpoint | Descripció | Retorna |
|--------|-----------|-------------|----------|
| `GET` | `/consumption/` | Llista tots els consums registrats | JSON amb consums |
| `GET` | `/consumption/summary` | Calcula un resum dinàmic (litres, percentatges, tipus d’ús) | JSON amb el resum |
| `GET` | `/anomalies/` | Llista totes les anomalies detectades | JSON amb alertes |

---

## 💾 Dades simulades (JSON)

Els fitxers JSON es troben a:

```
src/main/resources/dataJson
 ├── consumption.json
 ├── anomalies.json
 └── summary.json
```

Cada vegada que es fa una petició, el backend **llegeix directament els fitxers JSON** i genera les respostes sense necessitat d’una base de dades.

---

## ☁️ Desplegament al núvol

L’API de **AiguaApp** està desplegada mitjançant la plataforma **[Render](https://render.com)**.  
Pots accedir-hi directament aquí:

👉 **https://repteweb-backend.onrender.com/docs/**

### 🔹 Endpoints públics disponibles
- [https://repteweb-backend.onrender.com/consumption/](https://repteweb-backend.onrender.com/consumption/)
- [https://repteweb-backend.onrender.com/consumption/summary](https://repteweb-backend.onrender.com/consumption/summary)
- [https://repteweb-backend.onrender.com/anomalies/](https://repteweb-backend.onrender.com/anomalies/)

---

## 🚀 Execució local

### 🔧 Requisits
- Java 17+
- Maven 3.9+
- Swagger
- JSON
- Cross-Origin Resource Sharing (CORS)
- IntelliJ IDEA o VSCode amb suport per a Spring Boot

### ▶️ Executar l’aplicació

Des del terminal a l'arrel del projecte:
```bash
mvn spring-boot:run
```

o des d’IntelliJ, executant la classe:
```
AiguaAppApplication.java
```

El servidor s’aixeca per defecte a:
```
http://localhost:8080
```

---

## 🌐 Provar amb Postman o el navegador

- [http://localhost:8080/consumption/](http://localhost:8080/consumption/)
- [http://localhost:8080/consumption/summary](http://localhost:8080/consumption/summary)
- [http://localhost:8080/anomalies/](http://localhost:8080/anomalies/)

---

## 🧮 Exemple de resposta `/summary/`

```json
[
  {
    "neighborhood": "Eixample",
    "totalLiters": 2100.0,
    "percentageOfCity": 18.5,
    "mainUsageType": "industrial"
  }
]
```

---

## 📦 Dependències principals

| Dependència | Descripció |
|--------------|------------|
| **spring-boot-starter-web** | Proporciona suport per crear APIs REST amb Spring MVC i un servidor Tomcat integrat. |
| **spring-boot-starter-test** | Inclou llibreries per fer proves unitàries i integrades (JUnit, Mockito, etc.). |
| **spring-boot-devtools** | Permet la recàrrega automàtica del projecte durant el desenvolupament. |
| **springdoc-openapi-ui** | (Opcional) Genera documentació automàtica i interactiva de l’API amb Swagger UI. |

---

## 🧠 Lògica de negoci

### `FileDataService`

1. **Responsabilitat principal**
   - Gestiona la **lectura dels fitxers JSON** dins de `src/main/resources/dataJson`.
   - Controla els possibles errors d’entrada/sortida i retorna **respostes HTTP adequades**:
     - `200 OK` → fitxer llegit correctament  
     - `404 Not Found` → fitxer inexistent  
     - `500 Internal Server Error` → error de lectura o permisos

2. **Mètode principal**
   - `readJsonFile(String filename)` → retorna un `ResponseEntity<String>` amb el contingut o error corresponent.

---

### `ConsumtionController` i `AnomaliesController`

1. **Classes Controladores**
   - Gestionen les sol·licituds HTTP (`GET`) de `/consumption/` i `/anomalies/`.
   - Utilitzen el servei `FileDataService` per llegir els fitxers necessaris.

2. **Funcionament**
   - Cada controlador **crida el servei amb el nom del fitxer** que necessita:
     - `/consumption/` → `consumption.json`
     - `/consumption/summary` → `summary.json`
     - `/anomalies/` → `anomalies.json`
   - Els controladors no processen dades: només deleguen la lectura i retornen la resposta del servei.

3. **Avantatge**
   - El codi és més modular i mantenible: qualsevol canvi en la gestió de fitxers es fa només a `FileDataService.java`.

---

## 👥 Equip i crèdits

- @FerGimenezRoglia  
- @BlaiVO  
- @paideia2000  
- @AlvaroLMC

---

## 🧾 Llicència 

Aquest projecte es distribueix sota llicència **MIT**.  
El pots utilitzar i modificar lliurement citant-ne la font.

```
MIT License © 2025 AiguaApp Team
```

