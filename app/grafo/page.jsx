'use client';

import { Inter } from 'next/font/google';
import { Button, Label, Modal, Select, TextInput, Card } from 'flowbite-react';
import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { Doughnut } from 'react-chartjs-2';

const inter = Inter({ subsets: ['latin'] });

export default function Grafo() {
  const [instituto, setInstituto] = useState(null);
  const [pesquisador, setPesquisador] = useState(null);
  const [selectedInstituto, setSelectedInstituto] = useState('');
  const [tipoProducao, setTipoProducao] = useState(null);
  const [tipoVertice, setTipoVertice] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const { register, handleSubmit, formState: { errors }, watch,} = useForm();

  {/* Controle de cores nos campos de escolha de cor das arestas */}

  const [selectedColors, setSelectedColors] = useState(['', '', '']);

  {/* Controle de valores nos campos numéricos */}

  const [campo1, setCampo1] = useState(1);
  const [campo2, setCampo2] = useState(campo1);
  const [campo3, setCampo3] = useState(campo2 + 1);
  const [campo4, setCampo4] = useState(campo3);
  const [campo5, setCampo5] = useState(campo4 + 1);
  const [campo6, setCampo6] = useState(10000);
  
  function pesquisarGrafo({instituto, pesquisador, tipoProducao, tipoVertice}) {
    fetch(`http://localhost:8080/grafo?instituto=${instituto}&pesquisador=${pesquisador}&tipoProducao=${tipoProducao}&tipoVertice=${tipoVertice}`)
      .then((res) => res.json())
      .then((data) => {
        setData(data);
        agruparPorTipoVertice(data);
        console.log(data);
      })
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    setLoading(true);
    
    // Fazer a chamada para buscar a lista de institutos
    fetch('http://localhost:8080/instituto/list')
      .then((res) => res.json())
      .then((instituto) => {
        setInstituto(instituto);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    // Fazer a chamada para buscar a lista de pesquisadores
    fetch('http://localhost:8080/pesquisador/list')
      .then((res) => res.json())
      .then((pesquisador) => {
        setPesquisador(pesquisador);
        setLoading(false);
      })
    .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    setLoading(true);

    // Fazer a chamada para buscar os pesquisadores quando um instituto for selecionado
    if (selectedInstituto) {
      fetch(`http://localhost:8080/pesquisador/list?institutoNome=${selectedInstituto}`)
        .then((res) => res.json())
        .then((pesquisador) => {
          setPesquisador(pesquisador);
          setLoading(false);
        })
        .catch((err) => console.log(err));
    }
  }, [selectedInstituto]);

  useEffect(() => {
    setLoading(true);

    // Fazer a chamada para buscar os tipos de produção
    fetch('http://localhost:8080/tipoProducao')
      .then((res) => res.json())
      .then((tipoProducao) => {
        setTipoProducao(tipoProducao);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    setLoading(true);

      // Fazer a chamada para buscar os tipos de vertices
      fetch('http://localhost:8080/tipoVertice')
      .then((res) => res.json())
      .then((tipoVertice) => {
        setTipoVertice(tipoVertice);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, []);

  {/* Controle de cores nos campos de escolha de cor das arestas */}

  const handleSelect1Change = (event) => {
    const color = event.target.value;
    if (color !== selectedColors[1] && color !== selectedColors[2]) {
      setSelectedColors([color, selectedColors[1], selectedColors[2]]);
    }
  };

  const handleSelect2Change = (event) => {
    const color = event.target.value;
    if (color !== selectedColors[0] && color !== selectedColors[2]) {
      setSelectedColors([selectedColors[0], color, selectedColors[2]]);
    }
  };

  const handleSelect3Change = (event) => {
    const color = event.target.value;
    if (color !== selectedColors[0] && color !== selectedColors[1]) {
      setSelectedColors([selectedColors[0], selectedColors[1], color]);
    }
  };

  {/* Controle de valores nos campos numéricos */}

  const handleCampo2Change = (e) => {
    const value = parseInt(e.target.value);
    if (value >= campo1) {
      setCampo2(value);
      setCampo3(value + 1);
      if ((value + 1) > campo4){
        setCampo4(value + 1);
        if((value + 1) > campo5){
          setCampo5(value + 2);
        }
      }
    }
  };

  const handleCampo4Change = (e) => {
    const value = parseInt(e.target.value);
    if (value > campo3) {
      setCampo4(value);
      setCampo5(value + 1);
    }
  };

  const handleInstitutoChange = (event) => {
    // assume que o valor do option é o nome do instituto
    const selectedInstitutoNome = event.target.value;
    setSelectedInstituto(selectedInstitutoNome);
  };

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Grafo</h1>

        <form
          onSubmit={handleSubmit(pesquisarGrafo)}
          className='flex justify-between items-center"'
        >
          <div className="mr-4" id="select">
            <Select
              {...register('instituto')}
              value={selectedInstituto}
              onChange={handleInstitutoChange}
              id="campo"
              name="instituto"
            >
              <option value="">Instituto</option>
              {instituto &&
                instituto.map((instituto) => (
                  <option value={instituto.nome}>{instituto.nome}</option>
                ))}
            </Select>
          </div>

          <div className="mr-4" id="select">
            <Select {...register('pesquisador')} id="campo" name="pesquisador">
              <option value="">Pesquisador</option>
              {pesquisador &&
                pesquisador.map((pesquisador) => (
                  <option value={pesquisador.nome}>{pesquisador.nome}</option>
                ))}
            </Select>
          </div>

          <div className="mr-4" id="select">
            <Select
              {...register('tipoProducao')}
              id="campo"
              name="tipoProducao"
            >
              <option value="">Tipo Prod.</option>
              {tipoProducao &&
                tipoProducao.map((tipo) => (
                  <option key={tipo} value={tipo.nome}>
                    {tipo}
                  </option>
                ))}
            </Select>
          </div>

          <div className="mr-4" id="select">
            <Select {...register('tipoVertice')} name="tipoVertice" required={true}>
              <option value="">Tipo Vertice</option>
              {tipoVertice && tipoVertice.map((tipoVertice) => (
                <option key={tipoVertice} value={tipoVertice.nome}>
                  {tipoVertice}
                </option>
              ))}
            </Select>
          </div>

          <div className="flex justify-between items-center">
            <button type="submit" 
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            > 
              Aplicar
            </button>
          </div>
        </form>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg pt-5">
        <div className="">
          <form onSubmit={() => {}} className=" grid items-center gap-5 grid-cols-5 text-center" style={{ gridTemplateColumns: '50% 20% 20%' }}>
            <p className="text-2xl">Vértice (Cor)</p>
            <p className="text-2xl">Valor NP (início)</p>
            <p className="text-2xl">Valor NP (fim)</p>
          </form>

          <form onSubmit={() => {}} className=" grid items-center gap-5 grid-cols-5" style={{ gridTemplateColumns: '50% 20% 20%' }}>
            <div className="grid items-center gap-5 ">
              <div className="column">
                <Select value={selectedColors[0]} onChange={handleSelect1Change}>
                  <option value="">Cor 1</option>
                  <option>Vermelha</option>
                  <option>Roxa</option>
                  <option>Azul</option>
                  <option>Verde</option>
                  <option>Amarela</option>
                </Select>
                <Select value={selectedColors[1]} onChange={handleSelect2Change}>
                  <option value="">Cor 2</option>
                  <option>Vermelha</option>
                  <option>Roxa</option>
                  <option>Azul</option>
                  <option>Verde</option>
                  <option>Amarela</option>
                </Select>
                <Select value={selectedColors[2]} onChange={handleSelect3Change}>
                  <option value="">Cor 3</option>
                  <option>Vermelha</option>
                  <option>Roxa</option>
                  <option>Azul</option>
                  <option>Verde</option>
                  <option>Amarela</option>
                </Select>
              </div>
            </div>
            
            <div className="grid items-center gap-5 text-center">
              <div className="column">
                <input
                  disabled
                  type="number"
                  placeholder="Campo 1"
                  value={campo1}
                  style={{ color: 'black' }}
                />
                <input
                  disabled
                  type="number"
                  placeholder="Campo 3"
                  value={campo3}
                  style={{ color: 'black' }}
                />
                <input
                  disabled
                  type="number"
                  placeholder="Campo 5"
                  value={campo5}
                  style={{ color: 'black' }}
                />
              </div>
            </div>

            <div className="grid items-center gap-5 text-center">
              <div className="column">
                <input
                  type="number"
                  placeholder="Campo 2"
                  value={campo2}
                  onChange={handleCampo2Change}
                  style={{ color: 'black' }}
                />
                <input
                  type="number"
                  placeholder="Campo 4"
                  value={campo4}
                  onChange={handleCampo4Change}
                  style={{ color: 'black' }}
                />
                <input
                  disabled
                  type="number"
                  placeholder="Campo 6"
                  value={campo6}
                  style={{ color: 'black' }}
                />
              </div>
            </div>
          </form>

          <div className="pb-8 w-full">
            <div>
              Grafo aqui nessa área
            </div><div/>
          </div>
        </div>
      </div>
    </div>
  );
}