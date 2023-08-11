import React, { Component } from "react";

export default class RestClient {
  static baseUrl = "http://localhost:8081/oreillytestapp/products/";

  static getProducts(): Promise<any> {
    const url = `${RestClient.baseUrl}`;

    const promise = fetch(url).then((res) => res.json());
    return promise;
  }
}
