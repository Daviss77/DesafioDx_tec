document.getElementById("formIntegrante").addEventListener("submit", async function(e) {
    e.preventDefault();

    const integrante = {
        nome: document.getElementById("nome").value,
        funcao: document.getElementById("funcao").value
    };

    await fetch("/integrantes", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(integrante)
    });

    alert("Jogador cadastrado com sucesso!");
});