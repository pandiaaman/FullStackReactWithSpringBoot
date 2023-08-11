import React from "react";

export default function PanelB(props: any) {
  const name = props.name;
  const age = props.age;
  return (
    <div>
      PanelB
      <div>
        hello {name}, you are {age} years old
      </div>
    </div>
  );
}
