import React from "react";

import "./App.css";
import PanelA from "./panelsPrac/PanelA";
import PanelB from "./panelsPrac/PanelB";
import PanelC from "./panelsPrac/PanelC";
import RestClient from "./restClient/RestClient";

function App() {
  const PersonOne = {
    name: "aman",
    age: 25,
  };

  const PersonTwo = {
    name: "anisha",
    age: 24,
  };

  const PersonThree = {
    name: "ayaz",
    age: 25,
    skills: ["bethna", "sona", "khana", "gaana"],
  };
  return (
    <React.Fragment>
      <div className="App">
        <PanelA name={PersonOne.name} age={PersonOne.age} />
        <PanelB {...PersonTwo} />
        <PanelC {...PersonThree} />

        <div>
          <p>click on the below buttons to call the rest api using react</p>
          <button onClick={() => getAllProds()}>get all products</button>
        </div>
      </div>
    </React.Fragment>
  );
}

async function getAllProds() {
  const promiseresponse = await RestClient.getProducts();
  // promiseresponse.then((data) =>
  //   console.log(`all products :: ${JSON.stringify(data)}`)
  // );
  console.log(`all products :: ${JSON.stringify(promiseresponse)}`);
}

export default App;
