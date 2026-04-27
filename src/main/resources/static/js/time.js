async function carregarIntegrantes() {
    const response = await fetch("/integrantes");
    const integrantes = await response.json();

    const container = document.getElementById("listaIntegrantes");
    container.innerHTML = "";

    integrantes.forEach(i => {
        container.innerHTML += `
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="${i.id}">
                <label class="form-check-label">
                    ${i.nome} - ${i.funcao}
                </label>
            </div>
        `;
    });
}

async function criarTime() {
    const nome = document.getElementById("nomeTime").value;
    const data = document.getElementById("dataTime").value;

    const selecionados = document.querySelectorAll("input[type=checkbox]:checked");

    const idsIntegrantes = [];

    selecionados.forEach(cb => {
        idsIntegrantes.push(Number(cb.value)); // 🔥 só ID
    });

    const time = {
        nomeDoClube: nome,
        data: data,
        idsIntegrantes: idsIntegrantes
    };

    const response = await fetch("/times", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(time)
    });

    if (response.ok) {
        alert("Time criado com sucesso!");
    } else {
        const erro = await response.text();
        console.error("Erro:", erro);
        alert("Erro ao criar time!");
    }
}

document.getElementById("titulo").innerText =
    "⚽ Time da Semana - " + new Date().toLocaleDateString();

carregarIntegrantes();