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
  const [tipoProducao, setTipoProducao] = useState(null);
  const [tipoVertice, setTipoVertice] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const { register, handleSubmit, formState: { errors }, watch,} = useForm();
  
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
    
    fetch('http://localhost:8080/instituto/list')
      .then((res) => res.json())
      .then((instituto) => {
        setInstituto(instituto);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/pesquisador/list') //deve retornar apenas os pesquisadores que estão no instituto
      .then((res) => res.json())
      .then((pesquisador) => {
        setPesquisador(pesquisador);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/tipoProducao')
      .then((res) => res.json())
      .then((tipoProducao) => {
        setTipoProducao(tipoProducao);
        setLoading(false);
      })
      .catch((err) => console.log(err));

      fetch('http://localhost:8080/tipoVertice')
      .then((res) => res.json())
      .then((tipoVertice) => {
        setTipoVertice(tipoVertice);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, []);


  // useWatch({
  //   control,
  //   name: 'value1',
  // });

  // useWatch({
  //   control,
  //   name: 'value2',
  // });

  // useWatch({
  //   control,
  //   name: 'value3',
  // });

  // useWatch({
  //   control,
  //   name: 'value4',
  // });

  // useWatch({
  //   control,
  //   name: 'value5',
  // });

  // useWatch({
  //   control,
  //   name: 'input1',
  // });

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Grafo</h1>

        <form
          onSubmit={handleSubmit(pesquisarGrafo)}
          className='flex justify-between items-center"'
        >
          <div className="mr-4" id="select">
            <Select {...register('instituto')} id="camp" name="instituto">
              <option value="">Instituto</option>
              {instituto && instituto.map((instituto) => (
                  <option value={instituto.nome}>{instituto.nome}</option>
                ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select {...register('pesquisador')} id="campo" name="pesquisador">
              <option value="">Pesquisador</option>
              {pesquisador && pesquisador.map((pesquisador) => (
                  <option value={pesquisador.nome}>{pesquisador.nome}</option>
                ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select {...register('tipoProducao')} name="tipoProducao">
              <option value="">Tipo Prod.</option>
              {tipoProducao && tipoProducao.map((tipoProducao) => (
                <option key={tipoProducao} value={tipoProducao}>{tipoProducao}</option>
              ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select {...register('tipoVertice')} name="tipoVertice" required={true}>
              <option value="">Tipo Vertice</option>
              {tipoVertice && tipoVertice.map((tipoVertice) => (
                <option key={tipoVertice} value={tipoVertice}>{tipoVertice}</option>
              ))}
            </Select>
          </div>
          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            >
              Aplicar
            </button>
          </div>
        </form>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg pt-5">
        <div className="">
          <form
            onSubmit={() => {}}
            className=" grid items-center gap-5 grid-cols-3"
          >
            <p className="text-2xl">Vértice (Cor)</p>
            <p className="text-2xl">Valor NP (início)</p>
            <p className="text-2xl">Valor NP (fim)</p>
            
            <div className="grid-rows-5">
              <Select>
                <option value="">Vermelha</option>
                <option>Roxa</option>
                <option>Azul</option>
                <option>Verde</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Roxa</option>
                <option>Vermelha</option>
                <option>Azul</option>
                <option>Verde</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Azul</option>
                <option>Vermelha</option>
                <option>Roxa</option>
                <option>Verde</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Verde</option>
                <option>Vermelha</option>
                <option>Roxa</option>
                <option>Azul</option>
                <option>Amarela</option>
              </Select>
              <Select>
                <option value="">Amarela</option>
                <option>Vermelha</option>
                <option>Roxa</option>
                <option>Azul</option>
                <option>Verde</option>
              </Select>
            </div>

            {/*<div className="grid-rows-5 grid-cols-1">
              <TextInput disabled="true" value="1" {...register('input1')} />
              <TextInput
                disabled="true"
                value={
                  getValues('value1') && parseInt(getValues('value1'), 10) + 1
                }
                {...register('input2')}
              />
              <TextInput
                disabled="true"
                value={
                  getValues('value2') && parseInt(getValues('value2'), 10) + 1
                }
                {...register('input3')}
              />
              <TextInput
                disabled="true"
                value={
                  getValues('value3') && parseInt(getValues('value3'), 10) + 1
                }
                {...register('input4')}
              />
              <TextInput
                disabled="true"
                value={
                  getValues('value4') && parseInt(getValues('value4'), 10) + 1
                }
                {...register('input5')}
              />
            </div>

            <div className="grid-rows-5">
              <TextInput
                type="number"
                {...register('value1')}
                placeholder="5"
              />

              {console.log(getValues('input1'))}

              <TextInput
                type="number"
                min={parseInt(getValues('input1'))}
                {...register('value2')}
                placeholder="8"
              />
              <TextInput
                type="number"
                {...register('value3')}
                placeholder="10"
              />
              <TextInput
                type="number"
                {...register('value4')}
                placeholder="15"
              />
              <TextInput
                type="number"
                {...register('value5')}
                placeholder="20"
              />
              </div>*/}
          </form>
        </div>
      </div>
    </div>
  );
}
