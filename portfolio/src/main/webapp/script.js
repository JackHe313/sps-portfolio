// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomFunFact() {
    const greetings =
        ['As you can see from the image, I love golfing⛳️', 
          'I am from Beijing, China', 
          '你好，我叫何喆！(My name is Zhe He in Chinese)',
          'I also loves snow-boarding and skiing (I prefer snowboarding!)',
          'I love California! The weather is so awesome!'];
  
    // Pick a random greeting.
    const greeting = greetings[Math.floor(Math.random() * greetings.length)];
  
    // Add it to the page.
    const greetingContainer = document.getElementById('Funfact-container');
    greetingContainer.innerText = greeting;
  }
  
  function openTag(event, TabName) {
    var i, tabcontent, tablinks;
  
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(TabName).style.display = "block";
    event.currentTarget.className += " active";
  }

  async function showGreeting() {
      const responseFromServer= await fetch('/hello');
      const textFromResponse = await responseFromServer.json();
      const dateContainer = document.getElementById('greeting-container');
      dateContainer.innerHTML = '';
      dateContainer.appendChild(
        createListElement('First greeting: ' + textFromResponse[0]));
      dateContainer.appendChild(
        createListElement('Second greeting: ' + textFromResponse[1]));
      dateContainer.appendChild(
        createListElement('Third greeting: ' + textFromResponse[2]));
  }

  function createListElement(text) {
    const liElement = document.createElement('li');
    liElement.innerText = text;
    return liElement;
  }

  