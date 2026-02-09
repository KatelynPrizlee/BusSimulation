const bus = document.getElementById("bus");
const btnSim = document.getElementById("btnSim");

const ROWS = 12;
const COLS = 4;

function createBus(){
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

btnSim.onclick = async () => {
    createBus();

    const res = await fetch("/runSimuation"); /*might be error here?*/
    const steps = await res.json();

    for(const step of steps){
        await animateWalker(step);
    }

};

/*animation, this is breaking 100%*/

async function animateWalker(step){
    const walker = document.createElement("div");
    walker.className = `walker ${step.gender === "B" ? "boy" : "girl"}`;
    document.body.appendChild(walker);

    const busRect = bus.getBoundingClientRect();
    const startX = busRect.left + busRect.width / 2- 18; /*erm ???*/
    const startY = busRect.top - 50;

    walker.style.left = `${startX}px`;
    walker.style.top = `${stratY}px`;

    await delay(50);

    const aisleY = busRect.top + step.row * 92;
    walker.style.transform = `translate(0, ${aisleY - startY}px)`;
    await delay(800);

    const seatPos = getSeatPosition(step.row, step.col);
    walker.style.transform = `translate(0,${aisleY - startY}px)`;
    
    await delay(800);

    walker.remove();
    fillSeat(step);
}

function getSeatPosition(row, col){
    const visualCol = col < 2 ? col:col +1;
    const index = row * 5 + visualCol;
    const seat = bus.children[index];
    const rect = seat.getBoundingClientRect();

    return{
        x: rect.left + rect.width/2-18,
        y: rect.top + rect.height/2-18
    };
}

function fillSeat(step){
    const visualCol = step.col < 2? step.col: step.col + 1;
    const index = step.row * 5 + visualCol;
    const seat = bus.children[index];
    seat.classList.add(step.gender === "B" ? "boy" : "girl");
}

const delay = ms => new Promise(r => setTimeout(r,ms));

createBus();