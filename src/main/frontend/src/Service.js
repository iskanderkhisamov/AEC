import axios from 'axios'

class Service {

    retrieveScholar() {
        axios.get("http://localhost:8080/statistics/tupak").then(function (response) {
            console.log("well");
            console.log(response.data);
            console.log(response.status);
            console.log(response.statusText);
            console.log(response.headers);
            console.log(response.config);
        });
    }
}

export default new Service()