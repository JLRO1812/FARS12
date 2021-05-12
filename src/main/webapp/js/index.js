//Declaraciones
const nombre = document.getElementById('nombre');
const facultad = document.getElementById('facultad');
const regBtn = document.getElementById('regBtn');
const profesoresContainer = document.getElementById('profesoresContainer');


const registrar = ()=>{
    let profeObj = {
        id:0,
        nombre: nombre.value,
        facultad: facultad.value
    };
    console.log(JSON.stringify(profeObj));

    //Post
    let xhr = new XMLHttpRequest();
    //Response
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState===4){
            console.log(xhr.responseText);
            getAllProfesores();
        }
    });
    xhr.open('POST','http://localhost:8080/FirstApiRestS12_war/api/profesores/create');
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(JSON.stringify(profeObj)); //toJson

};

regBtn.addEventListener('click', registrar);


const getAllProfesores = ()=>{
        let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState===4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            console.log(response);
            profesoresContainer.innerHTML = '';
            for(let i=0; i<response.lengt; i++){
                let profesorDTO = response[i];
                let view = new ProfesorView(profesorDTO);
                view.onDeleteFinish = ()=>{
                    profesoresContainer.removeChild(document.getElementById('profesor'+profesorDTO.id));
                };
                profesoresContainer.appendChild(view.render());
            }
        }
    });
    xhr.open('GET', 'http://localhost:8080/FirstApiRestS12_war/api/profesores/all');
    xhr.send();
};
getAllProfesores();