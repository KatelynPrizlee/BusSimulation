const bus = document.getElementById("bus");
const btnSim = document.getElementById("btnSim");

const ROWS = 12;
const COLS = 4;


//uhh idk
function emptyBus(){
    bus.innerHTML = "";
    for(let r = 0; r <ROWS; r++){
        for(let c = 0; c<5; c++){
            const cell = document.createElement("div");
            if(c==2){
                cell.style.visibility = "hidden"; //hidden aisle
            }else{
                cell.className = "seat";
            }
            bus.appendChild(cell);
        }
    }
}



/*function runSimulation() {
    fetch("http://localhost:8080/api/runSim")
        .then(response => response.json())
        .then(data => {
            console.log(data);
            // Here is where you update the bus grid visually
        });
}
*/


btnSim.onclick = async () => {
    
    try{

    //createBus();

    const response = await fetch("/api/runSim"); /*might be error here?*/
    const data = await response.json();

    renderBus(data);

    //for(const step of steps){
    //    await animateWalker(step);
    //}

    }catch(error){
        console.error("Error running sim:",error);
    }

};

/*
async function runSim() {
    try{
        const response = await fetch("/api/runSim");
        const data = await response.json();

        console.log("Sim result:", data);

        renderBus(data);
    }catch(error){
        console.error("Error running sim",error);
    }
}
*/

function renderBus(seatArray){ //reading the array output
    const busGrid = document.getElementById("bus");

    busGrid.innerHTML = ""; //clear bus (these guys left)

    seatArray.forEach((row) => {
            
        const rowDiv = document.createElement("div");
        rowDiv.classList.add("row");

        row.forEach((seat,index)=>{

            const seatDiv = document.createElement("div");
            seatDiv.classList.add("seat");

            if(seat.taken && seat.student !== null){
                seatDiv.textContent = seat.student.ID +":" +  seat.student.Stop;

                if(seat.student.gender === "G"){
                    seatDiv.classList.add("girl");
                }else{
                    seatDiv.classList.add("boy");
                }

            }else{
                seatDiv.classList.add("empty");
            }

            rowDiv.appendChild(seatDiv);

            //aisle column
            if(index === 1){
                const aisle = document.createElement("div");
                aisle.classList.add("aisle");
                rowDiv.appendChild(aisle);
            }
        });

        busGrid.appendChild(rowDiv);

    });

}

//const delay = ms => new Promise(r => setTimeout(r,ms));