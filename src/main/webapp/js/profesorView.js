class ProfesorView{

    //Vista de un objeto
    //Acciones de un objeto: Comportamiento

    constructor(profesor){
        this.profesor = profesor;
        this.onDeleteFinish = null;
    }

    deleteProfesor = ()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState===4){
                var response = JSON.parse(xhr.responseText);
                if(response.message === 'Operacion exitosa'){
                    if(this.onDeleteFinish != null) this.onDeleteFinish();
                }else{
                    alert('No se pudo eliminar el profesor')
                }
            }
        });
        xhr.open('DELETE', 'http://localhost:8080/FirstApiRestS12_war/api/profesores/delete'+this.profesor.id);
    }

    render = ()=>{
        let component = document.createElement('div'); //<div></div>
        component.id = 'profesor'+this.profesor.id;
        component.className = 'profesorComponent';//<div class='profesorComponent'></div>
        let nombre = document.createElement('p'); //<p></p>
        let facultad = document.createElement('small'); //<small></small>
        let delBtn = document.createElement('button');
        
        delBtn.innerHTML = 'X';
        delBtn.className = 'delBtn'

        nombre.innerHTML = this.profesor.nombre; //<p>Andres Andrade</p>
        facultad.innerHTML = this.profesor.facultad; //<small>Ingenieria</small>

        component.appendChild(nombre); //<div><p>Andres Andrade</p></div>
        component.appendChild(facultad); //<div><p>Andres Andrade</p><small>Ingenieria</small></div>
        component.appendChild(delBtn);

        //Comportamiento
        delBtn.addEventListener('click', this.deleteProfesor)


        return component;
    }
}