import React from "react";

export default function PanelA(props: any) {
  const name = props.name;
  const age = props.age;
  return (
    <React.Fragment>
      PanelA
      <div>
        hello {name}, you are {age} years old
      </div>
    </React.Fragment>
  );
}
