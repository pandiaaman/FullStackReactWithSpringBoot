import React from "react";

export default function PanelC(props: any) {
  const name = props.name;
  const age = props.age;
  const skills = props.skills;
  return (
    <div>
      PanelC
      <div>
        hello {name}, you are {age} years old, you are skilled in:
        <ul>
          {skills.map((skill: string) => (
            <li>{skill}</li>
          ))}
        </ul>
        You useless man
      </div>
    </div>
  );
}
